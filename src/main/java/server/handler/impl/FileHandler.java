package server.handler.impl;

import com.alibaba.fastjson.JSON;
import common.*;
import server.SocketHolder;
import server.SocketWrapper;
import server.group.service.GroupService;
import server.handler.SocketHandler;
import util.DateUtils;
import util.PathUtil;
import util.StringUtil;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class FileHandler implements SocketHandler {
    GroupService groupService = new GroupService();

    public Object handle(Socket client, Object data) {
        if (client != null) {
            FileMessage fm = JSON.parseObject(data.toString(), FileMessage.class);
            OutputStream os = null;
            if(fm.isUpload()) {
                upload(client,fm,os);
            } else {
                FileInputStream fin = null;
                OutputStream outputStream = null;
                byte[] buffer = new byte[ConstantValue.BUFF_SIZE];
                try {
                    String dirPath = ConstantValue.CLIENT_RECEIVE_DIR +
                            File.separator +
                            fm.getTo();
                    File file = new File(PathUtil.combination(dirPath,fm.getTimer()+"-" +fm.getName()));
                    if(!file.exists() && file.isDirectory()) {
                        throw new IOException("Invalid file");
                    }
                    fin = new FileInputStream(file);
                    outputStream = client.getOutputStream();
                    int len;
                    long size = file.length();
                    while ( size > 0) {
                        len = fin.read(buffer,0, (int) Math.min(size,buffer.length) );
                        outputStream.write(buffer,0,len);
                        size -=len;
                    }
                    outputStream.flush();
                    //after send the file, inform the client
                }catch (IOException e) {
                    System.out.println("send file to client failed"+ e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * user upload file to server
     * @param client
     * @param fm
     * @param os
     */
    public void upload(Socket client, FileMessage fm, OutputStream os) {
        try {
            InputStream is = client.getInputStream();
            String dirPath = ConstantValue.SERVER_RECEIVE_DIR +
                    File.separator +
                    fm.getTo();
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            System.out.println(fm.getTimer());

            os = new FileOutputStream(new File(PathUtil.combination(dirPath, fm.getTimer() + "-" + fm.getName())));

            int total = 0;
            System.out.println(
                    String.format("Receiving file %s (%d bytes)", fm.getName(), fm.getSize())
            );

            while (!client.isClosed()) {
                if (is.available() > 0) {
                    byte[] buff = new byte[1024];
                    int len = -1;
                    while (is.available() > 0 && (len = is.read(buff)) != -1) {
                        os.write(buff, 0, len);
                        total += len;
                        System.out.println("RECEIVE BUFF [ " + len + " ]");
                    }
                    os.flush();
                    if (total >= fm.getSize()) {
                        System.out.println("completed");
                        break;
                    }
                }
            }

            //AFTER download this file
            //send a response message to inform client that the file was uploaded successful
            ReturnMessage rm = new ReturnMessage();
            rm.setKey(Key.TIP);
            rm.setSuccess(true);
            rm.setContent(fm);
            rm.setFrom(ConstantValue.SERVER_NAME);

            //send message to the room
            ArrayList<SocketWrapper> room = groupService.getRoom(fm.getTo());
            for (int i = 0; i < room.size(); i++) {
                SocketWrapper wrapper = room.get(i);
                if (wrapper != null) {
                    SendHelper.send(wrapper.getSocket(), rm);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("download error");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
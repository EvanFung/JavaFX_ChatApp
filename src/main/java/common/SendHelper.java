package common;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class SendHelper {
    private SendHelper() {
    }
    public synchronized static void send(Socket socket, BaseMessage message) {
        if (socket != null && !socket.isClosed()) {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                System.out.println(" [" + JSON.toJSON(message) + "] SEND AT " + new Date());
                out.println(JSON.toJSON(message));
                out.flush(); // ??
                Thread.sleep(ConstantValue.MESSAGE_PERIOD);
            } catch (Exception ignore) {
                System.out.println("Message send faild !" + ignore.getMessage());
            }
        }
    }



    public synchronized static void upload(Socket socket, File file) {
        if(socket != null && !socket.isClosed()) {
            InputStream is = null;
            try {
                OutputStream os = socket.getOutputStream();
                is = new FileInputStream(file);
                byte[] buff = new byte[ConstantValue.BUFF_SIZE];
                int len = -1;
                while ((len = is.read(buff)) != -1) {
                    os.write(buff, 0, len);
                }
                os.flush();
                Thread.sleep(ConstantValue.MESSAGE_PERIOD);
            } catch (Exception e) {
                System.out.println("File upload failed "  +e.getMessage() );
            } finally {
                if(is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

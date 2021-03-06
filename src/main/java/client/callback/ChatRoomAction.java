package client.callback;

import client.Client;
import client.ClientHolder;
import common.FileMessage;
import common.SendHelper;
import util.DateUtils;

import java.io.File;

public class ChatRoomAction {
    /**
     *
     * @param to to room name
     * @param file file to be transmitted
     */
    public static void upload(String to, File file) {
        //file name extension
        String ext = "";
        if(file.getName().indexOf(".") != -1) {
            try {
                ext = file.getName().substring(file.getName().indexOf(".") + 1);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            FileMessage message = new FileMessage();

            message.setName(file.getName());
            message.setExt(ext);
            message.setSize(file.length());
            message.setFrom(ClientHolder.getClient().getFrom());
            message.setTo(to);
            message.setTimer(DateUtils.getFormatDate());
            message.setUpload(true);
            //Send the file message(header)
            SendHelper.send(ClientHolder.getClient().getSocket(),message);
            //upload file
            SendHelper.upload(ClientHolder.getClient().getSocket(),file);
        }
    }
}

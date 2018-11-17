package client.callback;

import client.handler.HF;
import client.handler.impl.TipHdl;
import com.alibaba.fastjson.JSON;
import common.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.PathUtil;
import util.StringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class DefaultCallback implements Callback {
    private static ObservableList<ChatMessage> chatRecord = FXCollections.observableArrayList();

    @Override
    public void doWork(Socket server, Object data) {

        if (data != null) {
            BaseMessage message = JSON.parseObject(data.toString(), BaseMessage.class);
            switch (message.getType()) {
                case MessageType.CHAT:
                    handleChatMessage(data);
                    break;
                case MessageType.RETURN:
                    handleReturnMessage(data);
                    break;
                case MessageType.FILE:
                    handleFileMessage(server, data);
                    break;
            }
        }

    }

    private void handleChatMessage(Object data) {
        ChatMessage m = JSON.parseObject(data.toString(), ChatMessage.class);
        addMessage(m);
    }

    private void addMessage(ChatMessage message) {
        Platform.runLater(() -> {
            chatRecord.add(message);
        });
    }

    public static ObservableList getChatRecord() {
        return chatRecord;
    }

    private void handleReturnMessage(Object data) {
        ReturnMessage rm = JSON.parseObject(data.toString(), ReturnMessage.class);
        if (StringUtil.isNotEmpty(rm.getKey())) {
            switch (rm.getKey()) {
                case Key.LOGIN:
                    HF.getHandler(Key.LOGIN).handle(data);
                    break;
                case Key.REGISTER:
                    HF.getHandler(Key.REGISTER).handle(data);
                    break;
                case Key.LISTUSER:
                    HF.getHandler(Key.LISTUSER).handle(data);
                case Key.ENTERGROUP:
                    HF.getHandler(Key.ENTERGROUP).handle(data);
                    break;
                case Key.TIP:
                    HF.getHandler(Key.TIP).handle(data);
                    break;
            }
        }
    }

    /**
     * Receiving message
     *
     * @param server
     * @param data
     */
    private void handleFileMessage(Socket server, Object data) {
        FileMessage message = JSON.parseObject(data.toString(), FileMessage.class);
        if (message.getSize() > 0) {
            OutputStream os = null;
            try {
                if (server != null) {
                    InputStream is = server.getInputStream();
                    String dirPath = ConstantValue.CLIENT_RECEIVE_DIR +
                            File.separator +
                            message.getTo();
                    File directory = new File(dirPath);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    os = new FileOutputStream(new File(PathUtil.combination(dirPath, new Date().getTime() + "-" + message.getName())));
                    int total = 0;
                    while (!server.isClosed()) {
                        if (is.available() > 0) {
                            byte[] buff = new byte[ConstantValue.BUFF_SIZE];
                            int len = -1;
                            while (is.available() > 0 && (len = is.read(buff)) != -1) {
                                os.write(buff, 0, len);
                                total += len;
                                System.out.println("RECEIVE BUFF [ " + len + " ]");
                            }
                            os.flush();
                            if (total >= message.getSize()) {
                                System.out.println("RECEIVE BUFF [OK]");
                                break;
                            }
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println("Receive file failed !" + e.getMessage());
            } finally {
                if (os != null) {
                    try {
                        os.close();

                    } catch (Exception e) {
                        System.out.println(e.getMessage() + "close os failed");
                    }
                }
            }
        }
    }
}

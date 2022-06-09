package com.martian.aircraftwar.online;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    private static Socket socket;
    public static InputStream is;
    public static OutputStream os;

    public static void initConnection(){
        try {
            socket = new Socket("192.168.3.7", 8888);
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            Log.e("========", "未连接到服务器");
        }
    }

    public static boolean isConnected() {
        return socket != null;
    }

    public static void closeSocket(){
        if (socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                Log.e("========","关闭Socket失败");
            }
        }
    }

    public static String getMessage(){
        try {
            byte[] bytes = new byte[1024];
            int len = is.read(bytes);
            return new String(bytes,0,len);
        } catch (IOException e) {
            Log.e("========","从服务端获取信息失败" );
        }
        return null;
    }

    public static void sendMessage(String msg){
        try {
            os.write(msg.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            Log.e("========", "向服务端发送信息失败" );
        }
    }
}

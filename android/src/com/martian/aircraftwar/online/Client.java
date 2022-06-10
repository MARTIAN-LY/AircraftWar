package com.martian.aircraftwar.online;

import android.util.Log;

import com.martian.aircraftwar.rank.world.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Client {

    private static Socket socket = null;
    public static InputStream is = null;
    public static OutputStream os = null;

    private static Socket data_socket = null;
    private static InputStream data_is = null;
    private static OutputStream data_os = null;

    public static void initConnection() {
        try {
            Log.e("========", "执行：initConnection");
            socket = new Socket("192.168.3.7", 8888);
            Log.e("========", "initConnection没卡");
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            Log.e("========", "未连接到服务器");
        }
    }

    public static boolean isConnected() {
        return socket != null;
    }

    public static void closeSocket() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                Log.e("========", "关闭Socket失败");
            }
        }
    }

    public static String getMessage() {
        try {
            byte[] bytes = new byte[1024];
            int len = is.read(bytes);
            return new String(bytes, 0, len);
        } catch (IOException e) {
            Log.e("========", "从服务端获取信息失败");
        }
        return null;
    }

    public static void sendMessage(String msg) {
        try {
            os.write(msg.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            Log.e("========", "向服务端发送信息失败");
        }
    }

    /**
     * 世界排行
     */
    public static void initDataConnection() {
        try {
            Log.e("========", "执行：initDataConnection");
            data_socket = new Socket("192.168.3.7", 9999);
            Log.e("========", "initDataConnection没卡");
            data_is = data_socket.getInputStream();
            data_os = data_socket.getOutputStream();
            Log.e("========", "DataConnection获取流没问题");
        } catch (IOException e) {
            Log.e("========", "initDataConnection执行错误");
        }
    }

    /**
     * 世界排行
     */
    public static void sendUserMessage(Data data) {
        try {
            Log.e("========", "向服务器发送自己的数据");
            ObjectOutputStream oos = new ObjectOutputStream(data_os);
            oos.writeObject(data);
            oos.flush();
            data_socket.shutdownOutput();
            Log.e("========", "向服务器发送自己数据完毕");
        } catch (IOException e) {
            Log.e("========", "向服务器写对象失败");
        }
    }

    /**
     * 世界排行
     */
    public static boolean dataIsConnected() {
        return data_socket != null;
    }

    /**
     * 世界排行
     */
    public static ArrayList<Data> getUserMessage() {
        ArrayList<Data> list = new ArrayList<>();
        try {
            Log.e("=========", "从服务器接收数据排行中。。。");
            ObjectInputStream ois = new ObjectInputStream(data_is);
            list = (ArrayList<Data>) ois.readObject();
            Log.e("=========", "从服务器接收排行数据成功");
        } catch (Exception e) {
            Log.e("=========", "从服务器读取世界排行失败" + e);
        }
        return list;
    }

    /**
     * 世界排行
     */
    public static void closeDataSocket() {
        try {
            if (data_socket != null) {
                data_socket.close();
            }
        } catch (IOException e) {
            Log.e("========", "关闭DataSocket失败");
        }
    }
}

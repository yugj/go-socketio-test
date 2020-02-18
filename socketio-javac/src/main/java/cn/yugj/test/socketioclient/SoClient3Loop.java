package cn.yugj.test.socketioclient;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * @author yugj
 * @date 2020/2/18 15:52.
 */
public class SoClient3Loop {

    public static void main(String[] args) {
        String url = "http://localhost:8000";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            //失败重试次数
            options.reconnectionAttempts = 10;
            //失败重连的时间间隔
            options.reconnectionDelay = 1000;
            //连接超时时间(ms)
            options.timeout = 500;
            final Socket socket = IO.socket(url, options);

            //连接成功监听
            socket.on(Socket.EVENT_CONNECT, objects -> {
                socket.emit("notice", "hi,server");
                System.out.println("client: " + "连接成功");
            });

            //监听自定义msg事件
            socket.on("reply", args1 -> {

                String obj = (String) args1[0];
                System.out.println("reply from server :" + obj);

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("re send to server");
                socket.emit("notice", "notice s");

            });

            socket.on(Socket.EVENT_CONNECTING, objects -> System.out.println("client: " + "连接中"));
            socket.on(Socket.EVENT_CONNECT_TIMEOUT, objects -> System.out.println("client: " + "连接超时"));
            socket.on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client: " + "连接失败"));

            socket.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

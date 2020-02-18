package cn.yugj.test.socketioclient;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * @author yugj
 * @date 2020/2/17 17:01.
 */
public class SoClient {

    private static Socket socket;

    public static void main(String[] args) throws URISyntaxException {

        IO.Options options = new IO.Options();
        options.transports = new String[]{"websocket"};
        //失败重试次数
        options.reconnectionAttempts = 10;
        //失败重连的时间间隔
        options.reconnectionDelay = 1000;
        //连接超时时间(ms)
        options.timeout = 500;

        socket = IO.socket("http://localhost:8000", options);

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {

                System.out.println("call");
                socket.emit("notice", "notice s");

                socket.disconnect();
            }

        }).on("msg", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject) args[0];
                System.out.println("event:" + obj.toString());
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                String obj = (String) args[0];
                System.out.println("disconnect:" + obj.toString());
            }

        });

        System.out.println("socket connect");
        socket.connect();
    }
}

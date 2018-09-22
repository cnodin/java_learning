package ch02.nio.server;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/5/27
 * Time: 01:01
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class NioTimerServer {

    public static void main(String[] args) throws IOException {

        int port = 9393;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[1]);
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }

}
package ch02.nio.client;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/5/27
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class NioTimeClient {

    public static void main(String[] args) {
        int port = 9393;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[1]);
        }

        new Thread(new TimeClientHandler("127.0.0.1", port),
                "TimeClient-001").start();
    }

}

package org.pollux.safademo.bolt.server;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-04-04
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class BoltServerDemo {

    private MyBoltServer boltServer;

    private int port;

    public BoltServerDemo() {
        //1. create a rpc server with assigned port
        boltServer = new MyBoltServer(port);

        //2. add processor for connect and close event
//        boltServer.addConnectionEventProcessor();

    }


    public static void main(String[] args) {

    }
}

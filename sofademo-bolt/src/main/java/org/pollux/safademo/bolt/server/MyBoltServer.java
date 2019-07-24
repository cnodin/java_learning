package org.pollux.safademo.bolt.server;

import com.alipay.remoting.ConnectionEventHandler;
import com.alipay.remoting.ConnectionEventProcessor;
import com.alipay.remoting.ConnectionEventType;
import com.alipay.remoting.rpc.RpcServer;
import com.alipay.remoting.rpc.protocol.UserProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-04-04
 * Time: 11:00
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class MyBoltServer {

    private int port;

    private RpcServer rpcServer;

    public MyBoltServer(int port) {
        this.port = port;
        rpcServer = new RpcServer(this.port);
    }

    public MyBoltServer(int port, boolean manageFeatureEnabled) {
        this.port = port;
        this.rpcServer = new RpcServer(this.port, manageFeatureEnabled);
    }

    public MyBoltServer(int port, boolean manageFeatureEnabled, boolean syncStop) {
        this.port = port;
        this.rpcServer = new RpcServer(this.port, manageFeatureEnabled, syncStop);
    }

    public boolean start() {
        this.rpcServer.start();
        return true;
    }

    public RpcServer getRpcServer() {
        return this.rpcServer;
    }

    public void registerUserProcessor(UserProcessor<?> processor) {
        this.rpcServer.registerUserProcessor(processor);
    }

    public void addConnectionEventProcessor(ConnectionEventType eventType, ConnectionEventProcessor eventProcessor) {
        this.rpcServer.addConnectionEventProcessor(eventType, eventProcessor);
    }

}

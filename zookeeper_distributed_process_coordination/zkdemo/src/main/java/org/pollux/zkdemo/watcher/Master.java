package org.pollux.zkdemo.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-02-03
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Master implements Watcher {

    private ZooKeeper zk;
    private String hostPort;

    public Master(String hostPort) {
        this.hostPort = hostPort;
    }

    public void startZK() throws IOException {
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    public void stopZK() throws InterruptedException {
        zk.close();
    }

    @Override
    public void process(WatchedEvent e) {
        System.out.println(e);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Master master = new Master(args[0]);
        master.startZK();

        TimeUnit.SECONDS.sleep(30);
    }
}

package org.pollux.practice.java.concurrent.ch02;

/**
 * Created by spockwwang on 2016/11/5.
 */
public class DaemonDemo {

    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("i am alive");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
//        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);
    }

}

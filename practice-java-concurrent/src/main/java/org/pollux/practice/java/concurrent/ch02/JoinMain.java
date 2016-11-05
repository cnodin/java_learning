package org.pollux.practice.java.concurrent.ch02;

/**
 * Created by spockwwang on 2016/11/5.
 */
public class JoinMain {

    public volatile static int i = 0;
    
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for (int j = 0; j < 1000000; j++);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }
    
}

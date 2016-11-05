package org.pollux.practice.java.concurrent.ch02;

/**
 * Created by spockwwang on 2016/11/5.
 */
public class ThreadInterruptedDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("Interruted");
                        break;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Interruted when sleep");
                        Thread.currentThread().interrupt();
                    }

                    Thread.yield();
                }
            }
        };

        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }

}

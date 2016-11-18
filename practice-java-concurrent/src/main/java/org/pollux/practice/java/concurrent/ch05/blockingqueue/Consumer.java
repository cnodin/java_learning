package org.pollux.practice.java.concurrent.ch05.blockingqueue;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by spockwwang on 2016/11/18.
 */
public class Consumer implements Runnable {

    private BlockingQueue<PCData> queue;    //缓冲区

    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue){
        this.queue = queue;
    }

    public void run() {
        System.out.println("start Consumer id=" + Thread.currentThread().getId());

        Random r = new Random();    //随机等待时间

        try {
            while(true){
                PCData pcData = queue.take();
                if (null != pcData){
                    int re = pcData.getData() * pcData.getData();   //计算平方
                    System.out.println(MessageFormat.format("{0}*{1}={2}", pcData.getData(), pcData.getData(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}

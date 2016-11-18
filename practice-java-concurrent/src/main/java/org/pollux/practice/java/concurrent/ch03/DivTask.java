package org.pollux.practice.java.concurrent.ch03;

import java.util.concurrent.*;

/**
 * Created by spockwwang on 2016/11/10.
 */
public class DivTask implements Runnable {

    int a, b;

    public DivTask(int a, int b){
        this.a = a;
        this.b = b;
    }

    public void run() {
        double re = a / b;
        System.out.println(re);
    }

    public static void main(String[] args) {
        //ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        ExecutorService pools = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            pools.submit(new DivTask(100, i));
        }
    }
}

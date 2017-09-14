package org.pollux.practice.java.concurrent.ch04;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by spockwwang on 2016/11/15.
 */
public class ThreadLocalPerformace {

    public static final int GEN_COUNT = 1000000;

    public static final int THREAD_COUNT = 4;

    static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public static Random random = new Random(123);

    public static ThreadLocal<Random> randomThreadLocal = new ThreadLocal<Random>(){
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

}

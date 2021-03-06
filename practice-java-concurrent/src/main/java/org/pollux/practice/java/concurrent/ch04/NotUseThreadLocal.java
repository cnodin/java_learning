package org.pollux.practice.java.concurrent.ch04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by spockwwang on 2016/11/13.
 */
public class NotUseThreadLocal {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class ParseDate implements Runnable {

        int i = 0;
        public ParseDate(int i){
            this.i = i;
        }

        public void run() {
            try{
                Date t = sdf.parse("2015-03-29 19:20:" + i%60);
                System.out.println(i + ":" + t);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
    }
}


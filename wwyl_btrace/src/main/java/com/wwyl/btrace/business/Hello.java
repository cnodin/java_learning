package com.wwyl.btrace.business;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/7/1
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Hello {

    public static void main(String[] args) throws Exception {
        while(true) {
            Random random = new Random();
            execute(random.nextInt(3000));
        }
    }

    public static Integer execute(int sleepTime) throws InterruptedException {
        Thread.sleep(sleepTime);
        System.out.println("sleep time is=>" + sleepTime);
        return 0;
    }
}

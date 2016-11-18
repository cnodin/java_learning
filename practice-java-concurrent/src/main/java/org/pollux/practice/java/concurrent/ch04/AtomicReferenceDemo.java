package org.pollux.practice.java.concurrent.ch04;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 模拟账户充值，充值逻辑是当账户金额小于20，则一次性充值20刺激消费，但只允许充值一次
 *
 * Created by spockwwang on 2016/11/17.
 */
public class AtomicReferenceDemo {

    //使用AtomicReference无法解决A->B->A的问题，所以会造成充值会充两次
//    static AtomicReference<Integer> money = new AtomicReference<Integer>();

    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);

    public static void main(String[] args) {
        //模拟充值
        for (int i = 0; i < 3; i++) {
            final int timestamp = money.getStamp();
            new Thread() {
                @Override
                public void run() {
                    while(true){
                        Integer m = money.getReference();
                        if (m < 20){
                            if (money.compareAndSet(m, m+20, timestamp, timestamp + 1)){
                                System.out.println("金额小于20元，充值成功，余额：" + money.getReference() + "元");
                                break;
                            }else{
                                System.out.println("余额大于20元，无需充值");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }// end 模拟充值

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    final int timestamp = money.getStamp();
                    while(true){
                        Integer m = money.getReference();
                        if (m > 10){
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m-10, timestamp, timestamp + 1)){
                                System.out.println("成功消费10元，余额:" + money.getReference());
                                break;
                            }else{
                                System.out.println("没有足够的余额");
                                break;
                            }
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();// end 模拟消费线程
    }

}

package org.pollux.practice.java.concurrent.ch02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pollux on 9/19/17.
 */
public class BadLockOnInteger implements Runnable {

	public static Integer i = 0;

	public Integer lockObj = 0;

	static BadLockOnInteger instance = new BadLockOnInteger();

	@Override
	public void run() {
		for (int j = 0; j < 200000; j++){
			synchronized (i){
				i++;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println(i);
	}
}

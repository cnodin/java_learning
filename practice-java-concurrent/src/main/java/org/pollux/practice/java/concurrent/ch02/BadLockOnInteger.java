package org.pollux.practice.java.concurrent.ch02;

/**
 * Created by pollux on 9/19/17.
 */
public class BadLockOnInteger implements Runnable {

	public static Integer i = 0;

	public static String lockObj = "";

	static BadLockOnInteger instance = new BadLockOnInteger();

	@Override
	public void run() {
		for (int j = 0; j < 20000; j++){
			synchronized (lockObj){
				i++;
				lockObj = String.valueOf(i);
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

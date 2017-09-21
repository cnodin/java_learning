package org.pollux.practice.java.concurrent.ch02;

/**
 * Created by pollux on 9/19/17.
 */
public class AccountingSyncBad implements Runnable {

	static int i = 0;

	public synchronized void increase(){
		i++;
	}


	@Override
	public void run() {
		for (int j = 0; j < 20000; j++){
			increase();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AccountingSyncBad());
		Thread t2 = new Thread(new AccountingSyncBad());
		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println(i);
	}
}

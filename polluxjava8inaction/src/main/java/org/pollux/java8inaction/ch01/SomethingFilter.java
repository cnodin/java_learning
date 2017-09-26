package org.pollux.java8inaction.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SomethingFilter {

	public interface Predicate<T> {
		boolean test(T t);
	}

	/**
	 * 泛型过滤
	 *
	 * @param list 需要过滤的列表
	 * @param p		 进行条件判断的谓词
	 * @param <T>	 泛型原型
	 * @return		 符合条件的数据
	 */
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T e : list){
			if (p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}

	public static void process(Runnable r) {
		r.run();
	}

	public static void main(String[] args) {
		List<String> data = Arrays.asList("2", "3", "5", "8");

		List<String> evenNumbers = filter(data, (String i) -> Integer.valueOf(i) % 2 == 0);
		System.out.println(evenNumbers);

		Thread t = new Thread(() -> System.out.println("hello, lambda"));
		t.start();

		process(() -> System.out.println("hello, world"));

		final int portNumber = 1337;
		Runnable r = () -> System.out.println(portNumber);
		//portNumber = 2337;

	}

}

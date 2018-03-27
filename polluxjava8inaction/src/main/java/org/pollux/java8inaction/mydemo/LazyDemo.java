package org.pollux.java8inaction.mydemo;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/1/17
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class LazyDemo {

	public static void main (String[] args) {

//		Lazy<Double> lazy = Lazy.of(Math::random);
//		System.out.println(lazy.isEvaluated());
//
//		double val1 = lazy.get();
//		System.out.println(lazy.isEvaluated());
//
//		double val2 = lazy.get();
//		System.out.println("val1:" + val1);
//		System.out.println("val2:" + val2);

		long i = 123456789;
		double d = Double.parseDouble(String.format("%.2f", (double)i / 1000 / 1000));

		System.out.println(d);
	}

}

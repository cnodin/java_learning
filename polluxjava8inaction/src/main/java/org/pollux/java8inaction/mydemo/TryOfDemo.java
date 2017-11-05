package org.pollux.java8inaction.mydemo;

import io.vavr.collection.List;
import io.vavr.control.Try;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/1/17
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class TryOfDemo {

	private static int divide(int dividend, int divisor){
		return dividend / divisor;
	}

	private static Try<Integer> divide(Integer dividend, Integer divisor){
		return Try.of(() -> dividend / divisor);
	}


	public static void main (String[] args) {
		Try<Integer> result = divide(new Integer(10), new Integer(0));

		if (!result.isFailure())
			System.out.println(result.get());


		Function<Integer, Integer> square = (num) -> num + num;

		System.out.println(square.apply(3));

		List<String> list1 = List.of("1", "2", "3");

//		List<String> list = Collections.unmodifiableList(list1);
//		list.add("why not");
//		System.out.println("hello");
	}

}

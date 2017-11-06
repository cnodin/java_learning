package org.pollux.java8inaction.mydemo;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/1/17
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class OptionDemo {

	public static void main (String[] args) {
		Option<Object> noneOption = Option.of(null);
		Option<Object> someOption = Option.of("val");

		System.out.println(noneOption.toString());
		System.out.println(someOption.toString());

		Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
	}

}

package org.pollux.java8inaction.mydemo;

import java.util.Optional;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/1/17
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class MatchDemo {

	private static String intMatcher(int i ){
		return Match(i).of(
						Case($(1), "one"),
						Case($(2), "two"),
						Case($(3), "three")
		);
	}

	private static String typeMatcher(Object obj){
		return Match(obj).of(
						Case($(instanceOf(Integer.class)), t -> t.getClass().toString()),
						Case($(instanceOf(String.class)), t -> t.getClass().toString())
		);
	}

	private static Optional<String> check(){
		return Optional.ofNullable("aaa");
	}

	public static void main (String[] args) {
		System.out.println(intMatcher(1));
		System.out.println(intMatcher(2));
		System.out.println(intMatcher(3));

		System.out.println(typeMatcher(new Integer(1)));
		System.out.println(typeMatcher(new String("1")));

		check().map(
						x -> x.getClass().toString()
		);
	}

}

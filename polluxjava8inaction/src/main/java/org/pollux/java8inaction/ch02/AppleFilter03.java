package org.pollux.java8inaction.ch02;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 02/12/2017
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class AppleFilter03 {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

        List<Apple> result = AppleFilter02.filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        List<Apple> result1 = AppleFilter02.filterApples(inventory, x -> "red".equals(x.getColor()));

        result1.stream().forEach(System.out::println);
    }

}

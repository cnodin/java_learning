package org.pollux.java8inaction.ch02;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 02/12/2017
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test (Apple apple) {
        return "green".equals(apple.getColor());
    }
}

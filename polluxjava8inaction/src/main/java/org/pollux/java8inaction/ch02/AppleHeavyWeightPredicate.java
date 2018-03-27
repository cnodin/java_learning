package org.pollux.java8inaction.ch02;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 02/12/2017
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test (Apple apple) {
        return apple.getWeight() > 150;
    }
}

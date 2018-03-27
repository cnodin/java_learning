package org.pollux.java8inaction.ch03;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 03/12/2017
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class JavaLambdaExpression {

    public static void main(String[] args) {
//        (String s) -> s.length();
//        (Apple a) -> a.getWeight() > 150;
//        (int x, int y) -> {
//            System.out.println("Result:");
//            System.out.println(x+y);
//        };
//
//        () -> 42;
//        (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

//
//        () -> {};
//        () -> "Raoui";
//        () -> {return "Mario";}
//        (Integer i) -> return "Alan " + i;
//        (String s) -> {"Iron Man";}

    }

    private int portNumber = 1337;

    public void testVariable() {
//        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
//        portNumber = 2337;
    }

}

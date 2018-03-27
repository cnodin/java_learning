package org.pollux.java8inaction.ch02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 03/12/2017
 * Time: 00:03
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class AppleFilter04 {

    public interface Predicate<T> {
        boolean test (T apple);
    }

    public static <T> List<T> filterApples(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e : list){
            if (p.test(e)){
                result.add(e);
            }
        }
        return result;
    }
}

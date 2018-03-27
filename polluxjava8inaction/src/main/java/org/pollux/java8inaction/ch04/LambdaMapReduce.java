package org.pollux.java8inaction.ch04;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 27/02/2018
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class LambdaMapReduce {
    private static List<User> users = Arrays.asList(
            new User(1, "张三", 12,User.Sex.MALE),
            new User(2, "李四", 21, User.Sex.FEMALE),
            new User(3,"王五", 32, User.Sex.MALE),
            new User(4, "赵六", 32, User.Sex.FEMALE));

    public static void main(String[] args) {

        //按性别统计用户数
        Map<User.Sex, Integer> map = users.parallelStream()
                .collect(Collectors.groupingBy(User::getGender,
                        Collectors.summingInt(User::getId)));

        System.out.println("finished");
    }
}

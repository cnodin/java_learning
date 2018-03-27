package org.pollux.java8inaction.ch04;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 27/02/2018
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
@AllArgsConstructor
public class User {

    private int id;

    private String name;

    private int age;

    private Sex gender;

    public enum Sex {
        MALE,FEMALE
    }

}

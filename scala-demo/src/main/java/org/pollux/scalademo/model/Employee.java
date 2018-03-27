package org.pollux.scalademo.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/9/17
 * Time: 05:06
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
public class Employee {

	private String name;

	private Date birthday;

}

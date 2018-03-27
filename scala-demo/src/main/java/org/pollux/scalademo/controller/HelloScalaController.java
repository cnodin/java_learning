package org.pollux.scalademo.controller;

//import org.pollux.scalademo.model.Task;
import org.pollux.scalademo.model.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/9/17
 * Time: 05:00
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
public class HelloScalaController {

	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}

	@GetMapping("/task")
	public String task(){
		Task task = new Task();
		task.setId(1);

		return task.toString();
	}

}

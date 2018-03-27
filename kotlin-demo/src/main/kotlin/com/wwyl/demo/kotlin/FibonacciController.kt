package com.wwyl.demo.kotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
Created with IntelliJ IDEA.
User: pollux
Date: 27/03/2018
Time: 01:02
To change this template use File | Settings | File Templates.
Description:
 */
@RestController
@RequestMapping("/kt")
class FibonacciController {

    @GetMapping("/fibona")
    fun fibonacci(a: Int) = fibonacciSequence().take(a).toList()

}
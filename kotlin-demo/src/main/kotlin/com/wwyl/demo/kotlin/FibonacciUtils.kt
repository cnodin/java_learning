package com.wwyl.demo.kotlin

/**
Created with IntelliJ IDEA.
User: pollux
Date: 27/03/2018
Time: 01:18
To change this template use File | Settings | File Templates.
Description:
 */

fun fibonacciSequence(): Sequence<Long> {
    var a = 0L
    var b = 1L

    fun next(): Long {
        val result = a + b
        a = b
        b = result

        return a
    }

    return generateSequence(::next)
}
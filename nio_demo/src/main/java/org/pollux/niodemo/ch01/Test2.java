package org.pollux.niodemo.ch01;

import java.nio.CharBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/8/4
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Test2 {

    public static void main(String[] args) {
        char[] charArray = new char[] { 'a', 'b', 'c', 'd', 'e'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity() = " + buffer.capacity() + " limit() = " + buffer.limit());
        buffer.limit(3);
        System.out.println();
        System.out.println("B capacity() = " + buffer.capacity() + " limit() = " + buffer.limit());
        buffer.put(0, 'o');//1
        buffer.put(1, 'p');//2
        buffer.put(2, 'q');//3
        char c = buffer.get(3);
        buffer.put(3, 'r');//4
    }

}

package org.pollux.niodemo.ch01;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/8/4
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Test5 {

    public static void main(String[] args) {
        byte[] byteArray = new byte[] {1, 2, 3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

        System.out.println("byteBuffer.capacity = " + byteBuffer.capacity());
        System.out.println();

        byteBuffer.position(1);
        byteBuffer.mark();

        System.out.println("byteBuffer.position = " + byteBuffer.position());
        byteBuffer.position(2);
        System.out.println("byteBuffer.position = " + byteBuffer.position());
        byteBuffer.reset();
        System.out.println("byteBuffer.position = " + byteBuffer.position());

        System.out.println("byteBuffer.isDirect = " + byteBuffer.isDirect());
    }

}

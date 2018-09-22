package org.pollux.niodemo.ch01;

import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/8/5
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Test11 {

    public static void main(String[] args) {
        byte[] byteArray = new byte[] {1,2,3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

        byteBuffer.position(2);
        byteBuffer.mark();

        byteBuffer.flip();

        System.out.println("byteBufer.position = " + byteBuffer.position());
        System.out.println("byteBuffer.limit = " + byteBuffer.limit());

        try {
            byteBuffer.reset();
        } catch (InvalidMarkException e) {
            System.out.println("byteBuffer mark is losted");
        }
    }

}

package org.pollux.niodemo.ch01;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/8/5
 * Time: 15:23
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Test13_1 {

    public static void main(String[] args) {
        byte[] byteArray = new byte[] {1,2,3,4,5,6,7,8,9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        int remaining = byteBuffer.remaining();
        for (int i = 0; i < remaining; i++) {
            System.out.println(byteBuffer.get() + " ");
        }
        System.out.println();
        byteBuffer.limit(5);
//        byteBuffer.clear();
        byteBuffer.rewind();
        byteBuffer.put((byte)10);
        byteBuffer.put((byte)11);

//        for (int i = 0; i < remaining; i++) {
//            System.out.println(byteBuffer.get() + " ");
//        }

        while(byteBuffer.hasRemaining()) {
            System.out.print(byteBuffer.get() + " ");
        }

    }

}

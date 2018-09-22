package org.pollux.niodemo.ch01;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/8/5
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Test18 {

    public static void main(String[] args) {
        byte[] byteArray = {1,2,3,4,5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer.put((byte)7);

        ByteBuffer byteBuffer1 = byteBuffer.duplicate();
        ByteBuffer byteBuffer2 = byteBuffer.slice();

//        byteArray[4] = (byte)6;
        System.out.println();

    }

}

package org.pollux.niodemo.ch01;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/8/5
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Test6 {

    public static void main(String[] args) {
        byte[] byteArrayIn1 = {1,2,3,4,5,6,7,8};
        byte[] byteArrayIn2 = {55,66,77,88};

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put(byteArrayIn1);
        byteBuffer.position(2);

        byteBuffer.put(byteArrayIn2, 1, 3);
        System.out.print("A=");
        byte[] getByte = byteBuffer.array();
        for (int i = 0; i < getByte.length; i++) {
            System.out.print(getByte[i] + " ");
        }

        byteBuffer.position(1);
        byte[] byteArrayOut = new byte[byteBuffer.capacity()];
        byteBuffer.get(byteArrayOut, 3, 4);
        System.out.println("B=");
        for (int i = 0; i < byteArrayOut.length; i++) {
            System.out.print(byteArrayOut[i] + " ");
        }
    }

}

package org.pollux.niodemo.ch01;

import java.nio.CharBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/8/5
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Test11_1 {

    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(20);
        System.out.println("A position = " + charBuffer.position() + " limit = " + charBuffer.limit());
        charBuffer.put("我是中国人我在中华人民共和国");

        System.out.println("B position = " + charBuffer.position() + " limit = " + charBuffer.limit());
        charBuffer.position(0);
        System.out.println("C position = " + charBuffer.position() + " limit = " + charBuffer.limit());

        for (int i = 0; i < charBuffer.limit(); i++) {
            System.out.print(charBuffer.get());
        }

        System.out.println("D position = " + charBuffer.position() + " limit = " + charBuffer.limit());
        charBuffer.clear();
        System.out.println("E position = " + charBuffer.position() + " limit = " + charBuffer.limit());

        //继续写入
        charBuffer.put("我还是中国人");
        System.out.println("F position = " + charBuffer.position() + " limit = " + charBuffer.limit());
//        charBuffer.limit(charBuffer.position());
//        charBuffer.position(0);

        charBuffer.flip();

        System.out.println("G position = " + charBuffer.position() + " limit = " + charBuffer.limit());
        for (int i = 0; i < charBuffer.limit(); i++) {
            System.out.print(charBuffer.get());
        }
        System.out.println();
    }

}

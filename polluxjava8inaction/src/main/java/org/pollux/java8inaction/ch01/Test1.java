package org.pollux.java8inaction.ch01;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/6/4
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Test1 {

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        TestObject testObject = new TestObject();
        test1.test(testObject);

        System.out.println(testObject.getStr1());
    }

    private void test(TestObject testObject) {
        testObject.setStr1("test1");
        testObject.setStr2("test2");
    }

}

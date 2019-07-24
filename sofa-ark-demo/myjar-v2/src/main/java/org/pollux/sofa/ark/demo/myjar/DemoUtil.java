package org.pollux.sofa.ark.demo.myjar;

/**
 * @author wangbinbin
 * @version DemoUtil, 2019/6/29 8:57 wangbinbin Exp
 */
public class DemoUtil {

  public void test() {
    System.out.println("------------------------");
    System.out.println("myjar v2 classloader=" + this.getClass().getClassLoader());
    System.out.println("------------------------");
  }

}

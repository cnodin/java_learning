package org.pollux.java8inaction.mydemo;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-02-20
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class CacheLineEffect {

    private static final int LEN = 64;

    static long[][] arr;

    public static void main(String[] args) {
        arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[LEN];
            for (int j = 0; j < LEN; j++) {
                arr[i][j] = 0L;
            }
        }
        long sum = 0L;
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i++) {
            for (int j = 0; j < LEN; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("Loop times: " + (System.currentTimeMillis() - marked));

        marked = System.currentTimeMillis();
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum += arr[j][i];
            }
        }
        System.out.println("Loop times: " + (System.currentTimeMillis() - marked));
    }


}

package com.wwyl.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/7/1
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@BTrace
public class TraceHelloWorld {

    @TLS
    private static long startTime = 0;

    @OnMethod(clazz = "com.wwyl.btrace.business.Hello", method = "execute")
    public static void startMethod() {
        startTime = BTraceUtils.timeMillis();
    }

    @OnMethod(clazz = "com.wwyl.btrace.business.Hello", method = "execute", location = @Location(Kind.RETURN))
    public static void endMethod() {
        BTraceUtils.println(BTraceUtils.strcat("the class method execute time => ",
                BTraceUtils.str(BTraceUtils.timeMillis() - startTime)));
        BTraceUtils.println("------------------------------");
    }

    @OnMethod(clazz = "com.wwyl.btrace.business.Hello", method = "execute", location = @Location(Kind.RETURN))
    public static void traceExecute(@ProbeClassName String clazz, @ProbeMethodName String method, int sleepTime) {
        BTraceUtils.println(BTraceUtils.strcat("the class name => ", clazz));
        BTraceUtils.println(BTraceUtils.strcat("the class method => ", method));
        BTraceUtils.println(BTraceUtils.strcat("the class method params => ", BTraceUtils.str(sleepTime)));
    }
}

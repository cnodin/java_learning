import com.sun.btrace.services.impl.Printer;
import com.sun.btrace.annotations.*;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/7/1
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@BTrace public class TraceHelloWorld {

    @Injected(ServiceType.RUNTIME)
    private static Printer printer;

    @OnMethod(
            clazz="/com\\.wwyl\\.btrace\\..*/",
            method="${m}"
    )
    public static void m(@Self Object o, @ProbeClassName String probeClass, @ProbeMethodName String probeMethod) {
        printer.println("this = " + o);
        printer.print("entered " + probeClass);
        printer.println("." + probeMethod);
    }
}

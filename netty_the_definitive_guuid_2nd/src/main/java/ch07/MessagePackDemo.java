package ch07;

import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessagePacker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/6/10
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class MessagePackDemo {

    public static void main(String[] args) {
        List<String> src = new ArrayList<>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");
    }

}

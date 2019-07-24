package org.spockwang.arkdemo.hessian4;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.caucho.hessian.util.HessianFreeList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-07-21
 * Time: 00:44
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Hessian4Service {
    public byte[] serialize(Object obj) throws IOException {
        if (obj == null) { throw new NullPointerException();}

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(obj);
        return os.toByteArray();
    }

    public Object deserialize(byte[] bytes) throws IOException {
        if (bytes == null) { throw new NullPointerException(); }

        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        HessianInput hi = new HessianInput(is);
        return hi.readObject();
    }

    public void test() {
        HessianFreeList fl = new HessianFreeList(12);
    }
}

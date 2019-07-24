package org.spockwang.arkdemo.pojo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-07-21
 * Time: 00:50
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class SamplePoJo implements Serializable {

    public String name;

    public SamplePoJo(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

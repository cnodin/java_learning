package com.wwyl.swan.common.provider;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: spockwang
 * Date: 11/20/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
public class TimeProvider implements Serializable {

    private static final long serialVersionUID = -3301695478208950415L;

    public Date now() {
        return new Date();
    }
}
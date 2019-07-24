package com.wwyl.swan.common.model;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/1
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 * Description:Data transfer object that carries anthentication data
 */
public interface AuthenticationRequestBody {

    String getLogin();

    String getPassword();

    boolean isRememberMe();
}

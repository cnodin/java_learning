package com.wwyl.swan.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/1
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 * Description: simple implements of {@link AuthenticationRequestBody}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleAuthenticationRequestBody implements AuthenticationRequestBody {

    private String login;

    private String password;

    private boolean rememberMe;

}

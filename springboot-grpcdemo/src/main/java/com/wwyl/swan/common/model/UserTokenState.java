package com.wwyl.swan.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: spockwang
 * Date: 11/20/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class UserTokenState {
    private String jwtToken;
    private Long expiresIn;

    public UserTokenState() {
        this.jwtToken = null;
        this.expiresIn = null;
    }

    public UserTokenState(String jwtToken, long expiresIn) {
        this.jwtToken = jwtToken;
        this.expiresIn = expiresIn;
    }
}
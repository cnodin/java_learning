package cn.com.fubon.springboot.starter.jwt.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/10/28
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
public class JwtAuthenticationRequest {

    private String username;
    private String password;

}

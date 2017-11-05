package cn.com.fubon.springboot.starter.jwt.common;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/1
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 * Description: Default constants for authorities
 */
public class AuthoritiesConstant {

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String ADMIN = "ROLE_ADMIN";

    private AuthoritiesConstant(){throw new UnsupportedOperationException();}

}

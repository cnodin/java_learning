package com.wwyl.swan.modules.api;

import com.wwyl.lark.core.model.EnterpriseUserLoginInfo;
import com.wwyl.lark.core.model.UserLoginInfo;
import com.wwyl.lark.util.constant.GlobalConstant;
import com.wwyl.swan.common.utils.LocaleMessageSourceUtil;
import com.wwyl.swan.common.utils.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证的基础控制类<br/>
 * Date：2018-3-23 22:59:17<br/>
 * Author：sh.zhang<br/>
 * */
public class BaseController {


    @Value("${spring.profiles.active}")
    protected String environment;

    @Autowired
    protected RedisTemplate redisTemplate;

    @Autowired
    protected TokenHelper tokenHelper;

    @Autowired
    protected LocaleMessageSourceUtil localeMessageSourceUtil;

    /**
     * 从request中获取登录用户信息。通过request获取token，通过token从Redis中获取用户登录信息
     * Date：2018-3-24 14:52:56
     * @param request 当前request请求。不为空，否则返回null。
     * @return {UserLoginInfo} 用户登录信息
     * */
    protected UserLoginInfo getLoginUserInfoByRequest(HttpServletRequest request) {
        if(request.getAttribute(GlobalConstant.ENTERPRISEUSER_LOGIN_INFO) != null){
            UserLoginInfo userLoginInfo = (UserLoginInfo)request.getAttribute(GlobalConstant.USER_LOGIN_INFO);
            return userLoginInfo;
        }
        return null;
    }

    /**
     * 从request中获取登录用户信息。通过request获取token，通过token从Redis中获取用户登录信息
     * Date：2018-3-24 14:52:56
     * @param request 当前request请求。不为空，否则返回null。
     * @return {UserLoginInfo} 用户登录信息
     * */
    protected EnterpriseUserLoginInfo getEnterpriseUserLoginInfoByRequest(HttpServletRequest request) {
        if(request.getAttribute(GlobalConstant.ENTERPRISEUSER_LOGIN_INFO) != null){
            EnterpriseUserLoginInfo enterpriseUserLoginInfo = (EnterpriseUserLoginInfo)request.getAttribute(GlobalConstant.ENTERPRISEUSER_LOGIN_INFO);
            return enterpriseUserLoginInfo;
        }
        return null;
    }
}

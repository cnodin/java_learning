package com.wwyl.swan.common.utils;

import com.wwyl.lark.core.entity.SuperFullEntity;
import com.wwyl.lark.core.model.EnterpriseUserLoginInfo;
import com.wwyl.lark.core.model.UserLoginInfo;
import com.wwyl.lark.util.constant.GlobalConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Description: 身份工具类
 * @author z.ren_wb
 * 2018年5月19日
 */
public class IdentityUtil {
	
	/**
	 * Description: 判断当前用户是否为后台运营人员
	 * @Author z.ren_wb
	 * @return boolean
	 * 2018年5月19日 下午3:12:28
	 */
	public static boolean isUser(HttpServletRequest request) {
		UserLoginInfo loginInfo = (UserLoginInfo)request.getAttribute(GlobalConstant.USER_LOGIN_INFO);
		if(null == loginInfo || null == loginInfo.getId()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Description: 判断当前用户是否为企业客户
	 * @Author z.ren_wb
	 * @return boolean
	 * 2018年5月19日 下午3:12:28
	 */
	public static boolean isEnterprise(HttpServletRequest request) {
		EnterpriseUserLoginInfo loginInfo = (EnterpriseUserLoginInfo)request.getAttribute(GlobalConstant.ENTERPRISEUSER_LOGIN_INFO);
		if(null == loginInfo || null == loginInfo.getEcode()) {
			return false;
		}
		return true;
	}

	/**
	 * Description: 根据传入的loginInfo设置修改相关信息
	 * @Author z.ren_wb
	 * @return SuperFullEntity
	 * 2018年5月23日 下午11:05:21
	 */
	public static SuperFullEntity setLastModifyInfo(
			UserLoginInfo userloginInfo, EnterpriseUserLoginInfo enterpriseloginInfo, SuperFullEntity entity) {
		if(null != userloginInfo) {
			entity.setLastModifiedBy(userloginInfo.getId().toString());
			entity.setLastModifiedDate(new Date());
			return entity;
		}else if(null != enterpriseloginInfo ){
			entity.setLastModifiedBy(enterpriseloginInfo.getId().toString());
			entity.setLastModifiedDate(new Date());
			return entity;
		}
		return entity;
	}
	/**
	 * Description: 根据传入的loginInfo设置创建相关信息
	 * @Author z.ren_wb
	 * @return SuperFullEntity
	 * 2018年5月23日 下午11:05:21
	 */
	public static SuperFullEntity setCreateInfo(UserLoginInfo userloginInfo, EnterpriseUserLoginInfo enterpriseloginInfo,SuperFullEntity entity) {
		if(null != userloginInfo) {
			entity.setCreatedBy(userloginInfo.getId().toString());
			entity.setCreatedDate(new Date());
			return entity;
		}else if(null != enterpriseloginInfo ){
			entity.setCreatedBy(enterpriseloginInfo.getId().toString());
			entity.setCreatedDate(new Date());
			return entity;
		}
		return entity;
	}
}

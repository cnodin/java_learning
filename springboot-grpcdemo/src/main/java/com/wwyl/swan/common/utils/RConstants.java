package com.wwyl.swan.common.utils;

/**
 * 响应信息常量定义.
 */
public class RConstants {
	
	/**
	 * 响应返回码:成功
	 */
	public static final int SUCCESS = 000000;

	/**
	 * 响应返回码：未处理异常
	 */
	public static final int ERROR_OTHER = 999999;

	public static final int ERROR = 999999;

	public static final String ERROR_MSG = "未知错误";

	/**
	 * 响应返回描述:成功
	 */
	public static final String SUCCESS_MSG ="操作成功";
	
	/**
	 * 响应返回码:用户名或密码不正确
	 */
	public static final int LOGIN_INFO_FAILE =100001;
	
	/**
	 * 响应返回描述:用户名或密码不正确
	 */
	public static final String LOGIN_INFO_FAILE_MSG ="用户名或密码不正确";
	
	/**
	 * 响应返回码:账号被锁定
	 */
	public static final int LOGIN_STATUS_FAILE =100002;
	
	/**
	 * 响应返回描述:账号被锁定
	 */
	public static final String LOGIN_STATUS_FAILE_MSG ="账号被锁定";
	
	/**
	 * 响应返回码:菜单名称不能为空
	 */
	public static final int MEUN_NAME_BLANK =200000;
	
	/**
	 * 响应返回描述:菜单名称不能为空
	 */
	public static final String MEUN_NAME_BLANK_MSG ="菜单名称不能为空";
	
	/**
	 * 响应返回码:上级菜单不能为空
	 */
	public static final int MEUN_P_NAME_BLANK =200001;
	
	/**
	 * 响应返回描述:上级菜单不能为空
	 */
	public static final String MEUN_P_NAME_BLANK_MSG ="上级菜单不能为空";
	
	/**
	 * 响应返回码:菜单URL不能为空
	 */
	public static final int MEUN_URL_BLANK =200002;
	
	/**
	 * 响应返回描述:菜单URL不能为空
	 */
	public static final String MEUN_URL_BLANK_MSG ="菜单URL不能为空";
	
	/**
	 * 响应返回码:上级菜单只能为目录类型
	 */
	public static final int MEUN_P_TYPE0_FAILE =200003;
	
	/**
	 * 响应返回描述:上级菜单只能为目录类型
	 */
	public static final String MEUN_P_TYPE0_FAILE_MSG ="上级菜单只能为目录类型";
	
	/**
	 * 响应返回码:上级菜单只能为菜单类型
	 */
	public static final int MEUN_P_TYPE1_FAILE =200004;
	
	/**
	 * 响应返回描述:上级菜单只能为菜单类型
	 */
	public static final String MEUN_P_TYPE1_FAILE_MSG ="上级菜单只能为菜单类型";
	
	/**
	 * 响应返回码:系统菜单，不能删除
	 */
	public static final int MEUN_UNABLE_DROP =200005;
	
	/**
	 * 响应返回描述:系统菜单，不能删除
	 */
	public static final String MEUN_UNABLE_DROP_MSG ="系统菜单，不能删除";
	
	/**
	 * 响应返回码:请先删除子菜单或按钮
	 */
	public static final int MEUN_DROP_SON_FIRST =200006;
	
	/**
	 * 响应返回描述:请先删除子菜单或按钮
	 */
	public static final String MEUN_DROP_SON_FIRST_MSG ="请先删除子菜单或按钮";
	
	/**
	 * 响应返回码:请先删除子部门
	 */
	public static final int DEPT_DROP_SON_FIRST =300000;
	
	/**
	 * 响应返回描述:请先删除子部门
	 */
	public static final String DEPT_DROP_SON_FIRST_MSG ="请先删除子部门";
	
	/**
	 * 响应返回码:原密码不正确
	 */
	public static final int USER_PWD_ERROR =400000;
	
	/**
	 * 响应返回描述:原密码不正确
	 */
	public static final String USER_PWD_ERROR_MSG ="原密码不正确";
	
	/**
	 * 响应返回码:系统管理员不能删除
	 */
	public static final int USER_SYS_DEL_ERROR =400001;
	
	/**
	 * 响应返回描述:系统管理员不能删除
	 */
	public static final String USER_SYS_DEL_ERROR_MSG ="系统管理员不能删除";
	
	/**
	 * 响应返回码:当前用户不能删除
	 */
	public static final int USER_CUR_DEL_ERROR =400002;
	
	/**
	 * 响应返回描述:当前用户不能删除
	 */
	public static final String USER_CUR_DEL_ERROR_MSG ="当前用户不能删除";
	
}

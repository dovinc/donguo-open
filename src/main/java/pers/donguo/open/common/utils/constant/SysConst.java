package pers.donguo.open.common.utils.constant;

/**  
 * <p>Title: SysConst.java </p>
 * <p>Description: 系统常量</p>
 * @author Penguin  
 * @date 2019年10月3日  
 * @version 1.0  
 */  
public class SysConst {
	/**
	 * 系统唯一超管ID
	 */
	public static final Long SUPER_ADMIN_ID = 1L;
	/**
	 * 系统唯一根目录ID
	 */
	public static final Long ROOT_MENU_ID = 0L;
	
	/**
	 * 浏览器端的token header 头信息
	 */
	public static final String TOKEN_HEADER = "Authorization";
	/**
	 * token 修正【即为token字符串前加修正字符串】
	 */
	public static final String TOKEN_PREFIX = "Bearer";
	/**
	 * Token 过期时间（24小时）
	 */
	public static final int TOKEN_EXPIRATION_TIME = 86400;
}

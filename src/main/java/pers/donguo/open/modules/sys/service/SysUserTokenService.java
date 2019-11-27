/**
 * 
 */
package pers.donguo.open.modules.sys.service;

import pers.donguo.open.modules.sys.entity.SysUserToken;

/**
 * <p>Title: SysTokenService.java </p>
 * <p>Description: 系统用户UserToken 登录使用的token实体</p>
 * @author Penguin
 * @date 2019年10月4日
 * @version 1.0
 */
public interface SysUserTokenService {
	
	/**
	 * @title: createUserToken
	 * @Description: 创建userToken
	 * @param userId
	 * @return userToken 实体
	 */
	SysUserToken createUserToken(Long userId, String username);
	/**
	 * @title: findByToken
	 * @Description: SysUserTokenService's findByToken | 通过tokenStr 查询userToken
	 * @param token
	 * @return
	 */
	SysUserToken findByToken(String token);
	
	/**
	 * @title: expired
	 * @Description: SysUserTokenService's expired | 通过userId将userToken 失效
	 * @param userId
	 */
	void expired(Long userId);
	
	/**
	 * @title: expired
	 * @Description: SysUserTokenService's expired | 通过token字符串 将userToken 失效
	 * @param token
	 */
	void expired(String token);
	/**
	 * @title: refreshExpiredTime
	 * @Description: SysUserTokenService's refreshExpiredTime | 刷新token过期时间
	 * @param userToken
	 */
	void refreshExpiredTime(SysUserToken userToken);
}

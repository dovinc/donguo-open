package pers.donguo.open.modules.sys.params;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * <p>Title: userQuery.java </p>
 * <p>Description: 用户 保存时使用的参数类</p>
 * @author Penguin
 * @date 2019年11月9日
 * @version 1.0
 */
@Data
public class SysUserParam {
	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机号
	 */
	private String mobile;
	
	private Long[] roleIds;
}

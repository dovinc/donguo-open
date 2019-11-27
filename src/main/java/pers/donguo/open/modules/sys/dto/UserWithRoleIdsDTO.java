package pers.donguo.open.modules.sys.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
import pers.donguo.open.modules.sys.entity.SysUser;
/**
 * <p>Title: UserWithRoleIds.java </p>
 * <p>Description: 携带用户对应的RoleIds的UserDTO</p>
 * @author Penguin
 * @date 2019年11月21日
 * @version 1.0
 */
@Data
@ToString
public class UserWithRoleIdsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 用户的 拥有的权限列表List
	 */
	private Long[] roleIds;
	
	public UserWithRoleIdsDTO(SysUser user){
		this.userId = user.getUserId();
		this.salt = user.getSalt();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.mobile = user.getMobile();
	}

}

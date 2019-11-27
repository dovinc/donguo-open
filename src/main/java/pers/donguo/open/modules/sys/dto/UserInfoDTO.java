package pers.donguo.open.modules.sys.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;
import pers.donguo.open.modules.sys.entity.SysMenu;
import pers.donguo.open.modules.sys.entity.SysUser;
/**
 * <p>Title: UserInfoDTO.java </p>
 * <p>Description: 用户详情传输对象 【包含用户信息，菜单列表，权限列表】</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@Data
@ToString
public class UserInfoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private List<String> permissionList = new ArrayList<String>();
	
	/**
	 * 用户拥有的菜单List
	 */
	private List<SysMenu> menuList = new ArrayList<SysMenu>();
	
	public UserInfoDTO() {
		
	}

	public UserInfoDTO(String username, String email, String mobile, List<String> permissionList,
			List<SysMenu> menuList) {
		super();
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.permissionList = permissionList;
		this.menuList = menuList;
	}
	
	public UserInfoDTO(SysUser user){
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.mobile = user.getEmail();
	}
	
	public UserInfoDTO(SysUser user,List<String> permissionList, List<SysMenu> menuList){
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.mobile = user.getEmail();
		this.permissionList = permissionList;
		this.menuList = menuList;
	}


}

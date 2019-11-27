package pers.donguo.open.config.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import pers.donguo.open.modules.sys.entity.SysUser;

/**
 * <p>Title: SysUserDetails.java </p>
 * <p>Description: SpringSecurity UserDetails的实现类 ，持有系统user对象</p>
 * @author Penguin  
 * @date 2019年9月28日  
 * @version 1.0
 */
public class SysUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<GrantedAuthority> authorities;
	/**
	 * User
	 */
	private SysUser user;
	
	public SysUserDetails(SysUser user) {
		this.user = user;
	}
	public SysUserDetails(SysUser user, List<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}
	
	@Override
	public String getPassword() {
		//由于springSecurity的默认DaoAuthenticationProvider 不支持加盐加密。且在authentication中获取密码时，获取到的是表单中的密码明文
		//所以salt 由此处传入，在注入的MD5PasswordEncoder 中进行拆分和比对。
		return user.getSalt() + "," + user.getPassword();
	}


	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public List<GrantedAuthority> getAuthorities(){
		return this.authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * @title: getUserId
	 * @Description: SysUserDetails's getUserId 获取用户id
	 * @return
	 */
	public Long getUserId() {
		if(user == null) {
			return null;
		}
		return user.getUserId();
	}
	
	public SysUser getUser() {
		
		return user;
	}
	
	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
}

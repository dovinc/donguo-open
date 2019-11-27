package pers.donguo.open.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import pers.donguo.open.modules.sys.entity.SysUser;
import pers.donguo.open.modules.sys.service.SysUserService;

/**
 * ！！！未使用-------------在需要高级自定义功能时使用此类，注册新的Provider
 * <p>Title: A.java </p>
 * <p>Description: </p>
 * @author Penguin
 * @date 2019年10月7日
 * @version 1.0
 */
public class CustomAuthenticationProvider
  implements AuthenticationProvider {
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	@Qualifier("myMD5PasswordEncoder")
	private PasswordEncoder passwordEncoder;
	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
    	// 获取用户登录时输入的用户名
        String username = authentication.getName();
        // 根据用户名查询系统中的用户信息  获取用户列表中唯一的用户对象
        SysUser sysUser = sysUserService.getUser(username);
        // 如果用户列表为 null，说明查找用户功能出现异常，抛出 AuthenticationServiceException
        if (sysUser == null) {
            throw new AuthenticationServiceException(String.format("Searching user[%s] occurred error!", username));
        }
        
        //获取userDetails对象
        UserDetails userDetails = new SysUserDetails(sysUser);
        
        // 如果用户没有设置启用或禁用状态，或者用户被设为禁用，则抛出 DisabledException
        if (!userDetails.isEnabled()) {
            throw new DisabledException(String.format("User[%s] is disabled!", username));
        }
        // 如果用户没有过期状态或过期状态为 true 则抛出 AccountExpiredException
        if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException(String.format("User[%s] is expired!", username));
        }
        // 如果用户没有锁定状态或锁定状态为 true 则抛出 LockedException
        if (!userDetails.isAccountNonLocked()) {
            throw new LockedException(String.format("User[%s] is locked!", username));
        }
        // 如果用户登录时输入的密码和系统中密码匹配，则返回一个完全填充的 Authentication 对象
        if (sysUser.getPassword().equals(passwordEncoder.encode(authentication.getCredentials().toString()))) {
        	
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), authentication.getCredentials(), userDetails.getAuthorities());
        }
        // 如果密码不匹配则返回 null（此处可以抛异常，试具体应用场景而定）
        return null;
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
}

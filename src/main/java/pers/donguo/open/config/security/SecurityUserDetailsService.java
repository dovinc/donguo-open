package pers.donguo.open.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import pers.donguo.open.modules.sys.entity.SysUser;
import pers.donguo.open.modules.sys.service.SysUserService;

/**
 * <p>Title: SecurityUserDetailsService.java </p>
 * <p>Description: springSecurity 使用此Service 获取用户信息 以及需要的权限信息</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@Service("securityUserDetailService")
@Slf4j
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	log.info(username);
        //从数据库查询用户信息
        SysUser sysUser = sysUserService.getUser(username);
        if (sysUser == null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<GrantedAuthority> authorities = this.transformPermStrList(sysUserService.getPermissionList(sysUser.getUserId()));
        SysUserDetails userDetails = new SysUserDetails(sysUser, authorities);
        //查询权限信息
        return userDetails;
    }
    
    public List<GrantedAuthority> transformPermStrList(List<String> permStrList){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String permStr : permStrList) {
			if(StrUtil.isBlank(permStr)) {
				continue;
			}
			String[] permArr = permStr.split(",");
			for(String perm : permArr) {
				if(StrUtil.isNotBlank(perm)) {
					authorities.add(new SimpleGrantedAuthority(perm));
				}
			}
		}
    	return authorities;
    }
}
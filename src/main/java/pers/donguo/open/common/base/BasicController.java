package pers.donguo.open.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import pers.donguo.open.common.utils.constant.PageConst;
import pers.donguo.open.config.security.SysUserDetails;
import pers.donguo.open.modules.sys.entity.SysUser;
import pers.donguo.open.modules.sys.service.SysUserService;

/**
 * <p>Title: BasicController.java </p>
 * <p>Description: 基础controller</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 * @param <T>
 * @param <S>
 */
public abstract class BasicController<T, S extends IService<T>> {
	@Autowired
	protected S baseService;
	
	@Autowired
	protected SysUserService sysUserService;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 默认分页页码大小与页大小
	 */
	protected final Page<T> basicPage = new Page<>(PageConst.DEFAULT_PAGE, PageConst.DEFAULT_LIMIT);
	/**
	 * @title: getCurrentUserId
	 * @Description: BasicController's getCurrentUserId | 获取当前用户id
	 * @return
	 */
	protected final Long getCurrentUserId() {
		SysUserDetails sysUserDetails = getUserDetails();
		if(sysUserDetails == null)
			throw new NullPointerException("Authentication user  cannot be null!");
		return sysUserDetails.getUserId();
	}
	/**
	 * @title: getCurrentSysUser
	 * @Description: BasicController's getCurrentSysUser | 获取当前用户
	 * @return
	 */
	protected final SysUser getCurrentSysUser() {
		SysUserDetails sysUserDetails = getUserDetails();
		if(sysUserDetails == null)
			throw new NullPointerException("Authentication user  cannot be null!");
		return getUserDetails().getUser();
	}
	/**
	 * @title: getUserDetails
	 * @Description: BasicController's getUserDetails | 获取springSecurity principal对象 中的userDetails
	 * @return
	 */
	protected final SysUserDetails getUserDetails() {
		SecurityContext ctx = SecurityContextHolder.getContext();
	    Authentication auth = ctx.getAuthentication();
	    return (SysUserDetails) auth.getPrincipal();
	}
	
	
}

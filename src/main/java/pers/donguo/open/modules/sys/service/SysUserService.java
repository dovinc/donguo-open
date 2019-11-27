package pers.donguo.open.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import pers.donguo.open.modules.sys.entity.SysUser;
import pers.donguo.open.modules.sys.params.SysUserQuery;

public interface SysUserService extends IService<SysUser> {
	/**
	 * @title: getUser
	 * @Description: SysUserService's getUser | 通过用户名获取用户entity
	 * @param username
	 * @return
	 */
	SysUser getUser(String username);
	/**
	 * @title: getPermissionList
	 * @Description: SysUserService's getPermissionList | 权限列表List 【permissions 字段列表】
	 * @param userId
	 * @return
	 */
	List<String> getPermissionList(Long userId);
	/**
	 * @title: pageList
	 * @Description: SysUserService's pageList | 动态查询 分页列表
	 * @param page
	 * @param userQuery
	 * @return
	 */
	IPage<SysUser> pageList(Page<SysUser> page, SysUserQuery userQuery);
	/**
	 * @title: saveAndEncodePassword
	 * @Description: SysUserService's saveAndEncodePassword | 对密码进行加密并保存用户
	 * @param user
	 */
	void encodePasswordAndSave(SysUser user);
	/**
	 * @title: updateUser
	 * @Description: SysUserService's updateUser | 更新用户信息
	 * @param user
	 */
	void updateUser(SysUser user);
}

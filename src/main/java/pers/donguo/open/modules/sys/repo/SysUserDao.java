package pers.donguo.open.modules.sys.repo;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import pers.donguo.open.modules.sys.entity.SysUser;
/**
 * <p>Title: SysUserDao.java </p>
 * <p>Description: 系统用户dao</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {
	/**
	 * 通过用户名查询用户entity
	 * @param username
	 * @return
	 */
	SysUser findUserByUsername(String username);
	/**
	 * @title: findPermissionsByUserId
	 * @Description: SysUserDao's findPermissionsByUserId | 通过userId 查询对应menu 的授权信息
	 * @param userId
	 * @return
	 */
	String[] findPermissionsByUserId(Long userId);
	/**
	 * @title: findPermissions
	 * @Description: SysUserDao's findPermissions 超级管理员级别查询所有的权限令牌
	 * @return
	 */
	String[] findPermissions();
	/**
	 * @title: findMenuIdsByUserId
	 * @Description: SysUserDao's findMenuIdsByUserId | 通过userId 查询对应的menuId数组 menuIds
	 * @param userId
	 * @return
	 */
	Long[] findMenuIdsByUserId(Long userId);
}

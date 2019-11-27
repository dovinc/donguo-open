/**
 * 
 */
package pers.donguo.open.modules.sys.repo;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import pers.donguo.open.modules.sys.entity.SysUserRole;

/**
 * <p>Title: SysUserRole.java </p>
 * <p>Description: </p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {
	/**
	 * @title: findRoleIdsByUserId
	 * @Description: SysUserRoleDao's findRoleIdsByUserId | 通过用户id【userId】查询roleIds
	 * @param userId
	 * @return
	 */
	Long[] findRoleIdsByUserId(Long userId);
	/**
	 * @title: findUserIdsByRoleId
	 * @Description: SysUserRoleDao's findUserIdsByRoleId | 通过角色id【roleId】 查询userIds
	 * @param roleId
	 * @return
	 */
	Long[] findUserIdsByRoleId(Long roleId);
	/**
	 * @title: deleteByUserId
	 * @Description: SysUserRoleDao's deleteByUserId | 通过userId 删除所有关联关系
	 * @param userId
	 * @return
	 */
	int deleteByUserId(Long userId);
	/**
	 * @title: deleteByRoleId
	 * @Description: SysUserRoleDao's deleteByRoleId | 通过roleId 删除所有关联关系
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Long roleId);
	
	/**
	 * @title: deleteByRoleIdIn
	 * @Description: SysUserRoleDao's deleteByRoleIdIn | 通过角色数组 批量删除
	 * @param roleIds
	 * @return
	 */
	int deleteByRoleIdIn(Long[] roleIds);
}

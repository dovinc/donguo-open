package pers.donguo.open.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import pers.donguo.open.modules.sys.entity.SysUserRole;

/**
 * <p>Title: SysUserRoleService.java </p>
 * <p>Description: </p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
public interface SysUserRoleService extends IService<SysUserRole> {
	/**
	 * @title: findRoleIdsByUserId
	 * @Description: SysUserRoleService's findRoleIdsByUserId | 通过userId 查询对应的roleId数组
	 * @param userId
	 * @return
	 */
	public Long[] findRoleIdsByUserId(Long userId);
	/**
	 * @title: findUserIdsByRoleId
	 * @Description: SysUserRoleService's findUserIdsByRoleId | 通过roleId 查询对应的userId数组
	 * @param roleId
	 * @return
	 */
	public Long[] findUserIdsByRoleId(Long roleId);
	
	
	/**
	 * @title: modifyRoleUsers
	 * @Description: SysUserRoleService's modifyRoleUsers | 通过userId 修改userId 批量修改用户角色对应关系 【删除原有】 
	 * @param roleId
	 * @param userIds
	 * @return
	 */
	public void modifyRoleUsers(Long roleId, Long[] userIds);
	/**
	 * @title: modifyUserRoles
	 * @Description: SysUserRoleService's modifyUserRoles | 通过roleId 修改roleId 对应的用户角色关系 【删除原有】
	 * @param userId
	 * @param roleIds
	 */
	public void modifyUserRoles(Long userId, Long[] roleIds);
	
	/**
	 * @title: insertBatch
	 * @Description: SysUserRoleService's insertBatch | 批量保存用户角色关系
	 * @param userRoles
	 */
	public void insertBatch(List<SysUserRole> userRoles);
	/**
	 * @title: insertBatch
	 * @Description: SysUserRoleService's insertBatch | 批量保存用户角色关系 【直接使用userId 和roleId 组合为 userRole对象】
	 * @param userIds
	 * @param roleIds
	 */
	public void insertBatch(Long[] userIds, Long[] roleIds);
	
	/**
	 * @title: deleteByUserId
	 * @Description: SysUserRoleService's deleteByUserId | 通过用户id userId 删除 用户角色对应关系
	 * @param userId
	 */
	public void deleteByUserId(Long userId);
	
	/**
	 * @title: deleteByRoleId
	 * @Description: SysUserRoleService's deleteByRoleId | | 通过角色id roleId 删除 用户角色对应关系
	 * @param roleId
	 */
	public void deleteByRoleId(Long roleId);
	
}

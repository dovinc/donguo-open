package pers.donguo.open.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import pers.donguo.open.modules.sys.entity.SysRole;
import pers.donguo.open.modules.sys.params.SysRoleQuery;

/**
 * <p>Title: SysRoleService.java </p>
 * <p>Description: 角色 Service</p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
public interface SysRoleService extends IService<SysRole> {
	
	/**
	 * @title: pageList
	 * @Description: SysRoleService's pageList | 动态查询 分页列表
	 * @param page
	 * @param userQuery
	 * @return
	 */
	IPage<SysRole> pageList(Page<SysRole> page, SysRoleQuery roleQuery);
	/**
	 * @title: saveRole
	 * @Description: SysRoleService's saveRole | 保存角色 同时对角色对应的用户Id 进行刷新
	 * @param role
	 * @param menuIds
	 */
	void saveRole(SysRole role, Long[] menuIds);
	/**
	 * @title: updateRole
	 * @Description: SysRoleService's updateRole | 更新角色 同时对角色对应的用户Id 进行刷新
	 * @param role
	 * @param menuIds
	 */
	void updateRole(SysRole role, Long[] menuIds);
	
	/**
	 * @title: deleteRole
	 * @Description: SysRoleService's deleteRole | 删除角色 以及对应的角色用户对应关系
	 * @param roleId
	 */
	void deleteRole(Long roleId);
}

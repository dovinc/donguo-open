package pers.donguo.open.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;

import pers.donguo.open.modules.sys.entity.SysRoleMenu;

/**
 * <p>Title: SysRoleMenuService.java </p>
 * <p>Description: 角色菜单对应 Service</p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
	/**
	 * @title: findMenuIdByRoleIds
	 * @Description: SysRoleMenuService's findMenuIdsByRoleId | 通过roleIds 查询没有子节点的menuId数组【也就是叶子节点】
	 * @param roleIds
	 * @return
	 */
	public Long[] findReafMenuIdsByRoleIds(Long[] roleIds);
}

/**
 * 
 */
package pers.donguo.open.modules.sys.repo;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import pers.donguo.open.modules.sys.entity.SysRoleMenu;

/**
 * <p>Title: SysRoleMenuDao.java </p>
 * <p>Description: 角色与菜单menu的关联表  dao</p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {
	/**
	 * @title: findMenuIdByRoleIds
	 * @Description: SysRoleMenuService's findMenuIdByRoleId | 通过roleIds 查询没有子节点的menuId数组
	 * @param roleIds
	 * @return
	 */
	public Long[] findReafMenuIdsByRoleIds(Long[] roleIds);
	/**
	 * @title: deleteByRoleId
	 * @Description: SysRoleMenuDao's deleteByRoleId | 通过roleId 删除实例
	 * @param roleId
	 * @return
	 */
	public int deleteByRoleId(Long roleId);
	
	/**
	 * @title: deleteByRoleIdIn
	 * @Description: SysRoleMenuDao's deleteByRoleIdIn | 删除roleIds 数组中roleId 对应的所有实例
	 * @param roleIds
	 * @return
	 */
	public int deleteByRoleIdIn(Long[] roleIds);
}

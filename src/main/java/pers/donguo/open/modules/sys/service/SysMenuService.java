package pers.donguo.open.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import pers.donguo.open.modules.sys.entity.SysMenu;

/**
 * <p>Title: SysMenuService.java </p>
 * <p>Description: 系统菜单Service</p>
 * @author Penguin
 * @date 2019年10月5日
 * @version 1.0
 */
public interface SysMenuService extends IService<SysMenu> {

	/**
	 * @title: getUserMenuTreeList
	 * @Description: Get user's nav tree SysMenu list by permission filtering | 通过权限的过滤，获取用户所拥有的系统菜单导航树
	 * @param userId
	 * @return
	 */
	List<SysMenu> getUserMenuTreeList(Long userId);

	/**
	 * @title: getAllMenuTreeList
	 * @Description: Get all menu tree without menuId array filter | 获取所有的菜单树【不经过menuId数组过滤】
	 * @return
	 */
	List<SysMenu> getAllMenuTreeList();

	/**
	 * @title: getAllMenuTreeList
	 * @Description: Get all menu tree by menuId array filtering | 获取通过menuId过滤的所有的菜单树【经过menuIds过滤】
	 * @param menuIds
	 * @return
	 */
	List<SysMenu> getAllMenuTreeList(Long[] menuIds);

	/**
	 * 
	 * @title: getAllMenuTreeList
	 * @Description: Get the tree list for the SysMenu without menuId array filter | 获取所有的系统菜单列表 【不经过menuId数组过滤】
	 * @return
	 */
	List<SysMenu> getAllMenuTreeListWithoutBtn();

	/**
	 * @title: getAllMenuTreeListWithoutBtn
	 * @Description: Get the tree list for the SysMenu by menuId array filtering | 获取所有的系统菜单列表 【经过menuIds过滤】
	 * @param menuIds
	 * @return
	 */
	List<SysMenu> getAllMenuTreeListWithoutBtn(Long[] menuIds);

}

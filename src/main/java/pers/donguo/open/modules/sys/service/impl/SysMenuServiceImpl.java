
package pers.donguo.open.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.ArrayUtil;
import pers.donguo.open.common.utils.constant.MenuConst;
import pers.donguo.open.common.utils.constant.SysConst;
import pers.donguo.open.modules.sys.entity.SysMenu;
import pers.donguo.open.modules.sys.repo.SysMenuDao;
import pers.donguo.open.modules.sys.repo.SysUserDao;
import pers.donguo.open.modules.sys.service.SysMenuService;

@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {
	@Autowired
	SysUserDao sysUserDao;

	@Override
	public List<SysMenu> getUserMenuTreeList(Long userId) {
		List<SysMenu> userMenuTreeList;
		Long[] menuIds = null;
		if (userId != SysConst.SUPER_ADMIN_ID) {
			// 查询用户所拥有的所有菜单id(按钮除外)
			menuIds = sysUserDao.findMenuIdsByUserId(userId);
			if (ArrayUtil.isEmpty(menuIds)) {
				userMenuTreeList = new ArrayList<SysMenu>();
			} else {
				userMenuTreeList = this.getAllMenuTreeListWithoutBtn(menuIds);
			}
		} else {
			userMenuTreeList = this.getAllMenuTreeListWithoutBtn(menuIds);
		}
		return userMenuTreeList;
	}

	@Override
	public List<SysMenu> getAllMenuTreeList() {
		return this.getMenuTreeByTypeNotEquals(SysConst.ROOT_MENU_ID, null, null);
	}

	@Override
	public List<SysMenu> getAllMenuTreeList(Long[] menuIds) {
		return this.getMenuTreeByTypeNotEquals(SysConst.ROOT_MENU_ID, menuIds, null);
	}

	@Override
	public List<SysMenu> getAllMenuTreeListWithoutBtn() {
		return this.getMenuTreeByTypeNotEquals(SysConst.ROOT_MENU_ID, null, MenuConst.Type.BUTTON.getValue());
	}

	@Override
	public List<SysMenu> getAllMenuTreeListWithoutBtn(Long[] menuIds) {
		return this.getMenuTreeByTypeNotEquals(SysConst.ROOT_MENU_ID, menuIds, MenuConst.Type.BUTTON.getValue());
	}

	public List<SysMenu> getMenuTree() {
		List<SysMenu> menuTreeList = baseMapper.findByParentIdAndMenuIdInAndTypeNotEquals(SysConst.SUPER_ADMIN_ID, null,
				null);
		for (SysMenu menu : menuTreeList) {
			// 如果为 catalog（目录）
			if (menu.getType() == MenuConst.Type.CATALOG.getValue()) {
				menu.setMenuList(this.getMenuTreeByTypeNotEquals(menu.getMenuId(), null, null));
			}
		}
		return menuTreeList;
	}

	/**
	 * @title: getMenuTreeList
	 * @Description: ！递归调用，通过父Id 获取对应的SysMenu Tree List， 使用menuIds（一般为用户所拥有的） 过滤
	 * @param parentId 父菜单id
	 * @param menuIds menuId 数组，通过此数组对查询出的menu进行过滤
	 * @param type menu类型 【sql查询时不等于此类型】
	 * @return
	 */
	public List<SysMenu> getMenuTreeByTypeNotEquals(Long parentId, Long[] menuIds, Integer type) {
		List<SysMenu> menuTreeList;
		// 有权限菜单时根据权限菜单过滤,无菜单过滤时为超管admin ->menuIds是否为空
		menuTreeList = baseMapper.findByParentIdAndMenuIdInAndTypeNotEquals(parentId, menuIds, type);
		for (SysMenu menu : menuTreeList) {
			Integer currentMenuType = menu.getType();// 当前循环层的menuType
			if (type != null && type.equals(MenuConst.Type.BUTTON.getValue())) {// 如果取不为btn 则只取catalog（目录） 不取menu目录下
				if (currentMenuType.equals(MenuConst.Type.CATALOG.getValue())) {
					menu.setMenuList(getMenuTreeByTypeNotEquals(menu.getMenuId(), menuIds, type));
				}
			} else {// 如果取所有 则catalog与menu下 都取
				if (currentMenuType.equals(MenuConst.Type.CATALOG.getValue())
						|| currentMenuType.equals(MenuConst.Type.MENU.getValue())) {
					menu.setMenuList(getMenuTreeByTypeNotEquals(menu.getMenuId(), menuIds, type));
				}
			}
		}
		return menuTreeList;
	}

}
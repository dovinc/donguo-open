package pers.donguo.open.modules.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import pers.donguo.open.common.base.BasicController;
import pers.donguo.open.common.utils.R;
import pers.donguo.open.common.utils.constant.SysConst;
import pers.donguo.open.modules.sys.entity.SysMenu;
import pers.donguo.open.modules.sys.service.SysMenuService;
/**
 * <p>Title: SysMenuController.java </p>
 * <p>Description: 系统菜单控制器</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@RequestMapping("sys/menus")
@RestController
public class SysMenuController extends BasicController<SysMenu, SysMenuService> {
	/**
	 * @title: allMenu
	 * @Description: SysMenuController's list All menu's list | 所有菜单的List
	 * @param param
	 * @return
	 */
	@GetMapping()
	public R list(@RequestParam HashMap<String, String> param) {
		List<SysMenu> menus = baseService.list();
		return R.withResultObj("list", menus);
	}

	/**
	 * @title: page
	 * @Description: SysMenuController's page | 分页菜单列表
	 * @param param
	 * @return
	 */
	@GetMapping("/page")
	public R page(@RequestParam HashMap<String, String> param) {
		Page<SysMenu> pageObj;
		if (StrUtil.isBlank(param.get("page")) || StrUtil.isBlank(param.get("limit"))) {
			pageObj = basicPage;
		} else {
			Integer page = Integer.valueOf(param.get("page"));
			Integer limit = Integer.valueOf(param.get("limit"));
			pageObj = new Page<>(page, limit);
		}

		IPage<SysMenu> pageInfo = baseService.page(pageObj);
		List<SysMenu> menuList = pageInfo.getRecords();
		return R.withResultObj("page", menuList);
	}

	@GetMapping("/{menuId}")
	public R info(@PathVariable("menuId") Long menuId) {
		if (menuId == null)
			throw new NullPointerException("The param menuId can not be null!");
		SysMenu menu = baseService.getById(menuId);
		return R.withResultObj("menu", menu);
	}
	
	/**
	 * D: 无button的树形结构的menu列表
	 * 
	 * @return
	 */
	@GetMapping("/tree")
	public R tree() {
		return R.withResultObj("tree", baseService.getAllMenuTreeList());
	}

	/**
	 * D: 无button的树形结构的menu列表
	 * 
	 * @return
	 */
	@GetMapping("/tree-without-btn")
//	@PreAuthorize("hasAuthority('sys:menu:select')")
	public R treeWithoutBtn() {
		List<SysMenu> menuTreeList = new ArrayList<SysMenu>(1);
		// 添加顶级菜单
		SysMenu root = new SysMenu();
		root.setMenuId(SysConst.ROOT_MENU_ID);
		root.setName("顶级菜单");
		root.setParentId(-1L);
		root.setMenuList(baseService.getAllMenuTreeListWithoutBtn());
		menuTreeList.add(root);
		return R.withResultObj("tree", menuTreeList);
	}
	
	/**
	 * @title: userMenuList
	 * @Description: 通过token获取的MenuList
	 * @param token
	 * @return
	 */
	@GetMapping("/user/{userId}")
	public R userMenuList(@PathVariable("userId") Long userId) {
		if (userId == null)
			userId = getCurrentUserId();
		List<SysMenu> userMenuList = baseService.getUserMenuTreeList(userId);
		return R.withResultObj("userMenuList", userMenuList);
	}

	@PostMapping
	public R save(SysMenu menu) {
		baseService.save(menu);
		return R.ok("保存成功");
	}

	@PutMapping
	public R update(SysMenu menu) {
		baseService.updateById(menu);
		return R.ok("修改成功");
	}

	@DeleteMapping("/{menuId}")
	@PreAuthorize("hasAuthority('sys:menu:delete')")
	public R delete(@PathVariable Long menuId) {
		baseService.removeById(menuId);
		return R.ok("删除成功");
	}

	@DeleteMapping("/batch")
	@PreAuthorize("hasAuthority('sys:menu:delete')")
	public R delete(Long[] menuIds) {
		if (baseService.removeByIds(CollUtil.toList(menuIds)))
			return R.ok("批量删除成功");
		else
			return R.error("批量删除失败");
	}
}

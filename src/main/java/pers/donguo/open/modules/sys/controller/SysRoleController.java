package pers.donguo.open.modules.sys.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.collection.CollUtil;
import pers.donguo.open.common.base.BasicController;
import pers.donguo.open.common.utils.R;
import pers.donguo.open.modules.sys.dto.RoleInfoDTO;
import pers.donguo.open.modules.sys.dto.base.PageDTO;
import pers.donguo.open.modules.sys.entity.SysRole;
import pers.donguo.open.modules.sys.params.SysRoleModifyRoleUsersParam;
import pers.donguo.open.modules.sys.params.SysRoleParam;
import pers.donguo.open.modules.sys.params.SysRoleQuery;
import pers.donguo.open.modules.sys.params.base.PageQuery;
import pers.donguo.open.modules.sys.service.SysRoleMenuService;
import pers.donguo.open.modules.sys.service.SysRoleService;
import pers.donguo.open.modules.sys.service.SysUserRoleService;
/**
 * <p>Title: SysRoleController.java </p>
 * <p>Description: 角色控制器</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@RequestMapping("sys/roles")
@RestController
public class SysRoleController extends BasicController<SysRole, SysRoleService> {
	
	@Autowired
	SysRoleMenuService sysRoleMenuService;
	@Autowired
	SysUserRoleService sysUserRoleService;
	/**
	 * @title: list
	 * @Description: SysRoleController's list All role's list | 分页List
	 * @param param
	 * @return
	 */
	@GetMapping()
	public R list(@Validated PageQuery pageQuery, @Validated SysRoleQuery roleQuery) {
		Integer page = pageQuery.getPage();
		Integer limit = pageQuery.getLimit();
		IPage<SysRole> pageInfo = baseService.pageList(new Page<SysRole>(page, limit), roleQuery);
		PageDTO pageDTO = new PageDTO(pageInfo);
		return R.withResultObj(pageDTO);
	}

	/**
	 * @title: all
	 * @Description: SysRoleController's list All role's list | 所有role的List
	 * @param param
	 * @return
	 */
	@GetMapping("/all")
	public R all(@Validated SysRoleQuery roleQuery) {
		List<SysRole> roleList = baseService.list();
		return R.withResultObj(roleList);
	}

	@GetMapping("/{roleId}")
	public R info(@PathVariable("roleId") Long roleId) {
		if (roleId == null)
			throw new NullPointerException("The param roleId can not be null!");
		SysRole role = baseService.getById(roleId);
		return R.withResultObj("role", role);
	}
	@GetMapping("/user-ids/{roleId}")
	public R roleUserIds(@PathVariable("roleId") Long roleId) {
		if (roleId == null)
			throw new NullPointerException("The param roleId can not be null!");
		Long[] userIds = sysUserRoleService.findUserIdsByRoleId(roleId);
		return R.withResultObj("userIds", userIds);
	}
	
	@GetMapping("/leaf-menuIds")
	public R leafMenuIds(Long[] roleIds) {
		Long[] menuIds = sysRoleMenuService.findReafMenuIdsByRoleIds(roleIds);
		return R.withResultObj("menuIds", menuIds);
	}
	
	@GetMapping("details/{roleId}")
	public R details(@PathVariable("roleId") Long roleId) {
		if (roleId == null)
			throw new NullPointerException("The param roleId can not be null!");
		SysRole role = baseService.getById(roleId);
		//查询角色对应的菜单
		Long[] menuIds = sysRoleMenuService.findReafMenuIdsByRoleIds(new Long[]{roleId});
		RoleInfoDTO roInfo = new RoleInfoDTO(role, menuIds) ;
		return R.withResultObj("role", roInfo);
	}

	@PostMapping
	public R save(@RequestBody @Validated SysRoleParam roleParam) {
		SysRole role = new SysRole();
		role.setRoleName(roleParam.getRoleName());
		role.setDescription(roleParam.getDescription());
		role.setCreateUserId(getCurrentUserId());
		role.setCreateTime(new Date());
		baseService.saveRole(role, roleParam.getMenuIds());
		return R.ok("保存成功");
	}

	@PutMapping
	public R update(@RequestBody @Validated SysRoleParam roleParam) {
		SysRole role = new SysRole();
		role.setRoleId(roleParam.getRoleId());
		role.setRoleName(roleParam.getRoleName());
		role.setDescription(roleParam.getDescription());
		role.setCreateUserId(getCurrentUserId());
		role.setCreateTime(new Date());
		baseService.updateRole(role, roleParam.getMenuIds());
		return R.ok("修改成功");
	}

	@DeleteMapping("/{roleId}")
	@PreAuthorize("hasAuthority('sys:role:delete')")
	public R delete(@PathVariable Long roleId) {
		baseService.deleteRole(roleId);
		return R.ok("删除成功");
	}

	@DeleteMapping("/batch")
	@PreAuthorize("hasAuthority('sys:role:delete')")
	public R delete(Long[] roleIds) {
		if (baseService.removeByIds(CollUtil.toList(roleIds)))
			return R.ok("批量删除成功");
		else
			return R.error("批量删除失败");
	}
	/**
	 * @title: modifyRoleUsers
	 * @Description: SysRoleController's modifyRoleUsers | 重新分配角色所拥有的用户
	 * @param param
	 * @return
	 */
	@PostMapping("/modify-role-users")
	public R modifyRoleUsers(@Validated @RequestBody SysRoleModifyRoleUsersParam param) {
		sysUserRoleService.modifyRoleUsers(param.getRoleId(), param.getUserIds());
		return R.ok("用户分配成功！");
	}
}

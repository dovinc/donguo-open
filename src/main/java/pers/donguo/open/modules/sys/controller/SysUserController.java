package pers.donguo.open.modules.sys.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import cn.hutool.core.util.ArrayUtil;
import pers.donguo.open.common.base.BasicController;
import pers.donguo.open.common.utils.R;
import pers.donguo.open.common.utils.constant.SysConst;
import pers.donguo.open.modules.sys.dto.UserInfoDTO;
import pers.donguo.open.modules.sys.dto.UserWithRoleIdsDTO;
import pers.donguo.open.modules.sys.dto.base.PageDTO;
import pers.donguo.open.modules.sys.entity.SysMenu;
import pers.donguo.open.modules.sys.entity.SysUser;
import pers.donguo.open.modules.sys.params.SysUserParam;
import pers.donguo.open.modules.sys.params.SysUserQuery;
import pers.donguo.open.modules.sys.params.base.PageQuery;
import pers.donguo.open.modules.sys.service.SysMenuService;
import pers.donguo.open.modules.sys.service.SysUserRoleService;
import pers.donguo.open.modules.sys.service.SysUserService;

/**
 * <p>Title: SysUserController.java </p>
 * <p>Description: 系统用户SysUser 控制器</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@RestController
@RequestMapping("/sys/users")
public class SysUserController extends BasicController<SysUser,SysUserService> {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * @title: currentDetailInfo
	 * @Description: SysUserController's currentDetailInfo 携带当前用户的权限信息和 菜单列表
	 * @return
	 */
	@GetMapping("/details")
	public R currentDetailInfo() {
		SysUser user = getCurrentSysUser();
		List<String> permissionList = sysUserService.getPermissionList(getCurrentUserId());
		List<SysMenu> menuList =  sysMenuService.getUserMenuTreeList(getCurrentUserId());
		//构造DTO 传输对象
		UserInfoDTO userInfo = new UserInfoDTO(user, permissionList, menuList);
		return R.withResultObj("userInfo", userInfo);
	}
	@GetMapping("/{userId}/details-with-role-ids")
	public R detailsWithRoleIds(@PathVariable Long userId) {
		SysUser user = sysUserService.getById(userId);
		Long[] roleIds = sysUserRoleService.findRoleIdsByUserId(userId);
		//构造DTO 传输对象
		UserWithRoleIdsDTO userDTO = new UserWithRoleIdsDTO(user); 
		userDTO.setRoleIds(roleIds);
		return R.withResultObj(userDTO);
	}
	
	@GetMapping("{userId}")
//	@PreAuthorize("has")
	public R info(@PathVariable Long userId) {
		SysUser sysUser = baseService.getById(userId);
		logger.info("SysUser Info method userId:{}->SysUser:{}", userId, sysUser);
		return R.ok("sysUser", sysUser);
	}

	@GetMapping("/token")
	public R info(String token) {
		R r = new R();
		r.put("code", 20000);
		Map<String, Object> map = new HashMap<>();
		map.put("introduction", "I am a super administrator");
		map.put("avatar", "I am a super administrator");
		map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
		map.put("name", "Super Admin");
		r.put("info", map);
		return r;
	}

	@GetMapping()
//	@PreAuthorize("hasAuthority('sys:user:list')")
	public R list(@Validated PageQuery pageQuery, @Validated SysUserQuery userQuery) {
		Integer page = pageQuery.getPage();
		Integer limit = pageQuery.getLimit();
		IPage<SysUser> pageInfo = baseService.pageList(new Page<SysUser>(page, limit), userQuery);
		PageDTO pageDTO = new PageDTO(pageInfo);
		return R.withResultObj(pageDTO);
	}
	
	@GetMapping("/all")
//	@PreAuthorize("hasAuthority('sys:user:list')")
	public R listAll(@Valid SysUserQuery userQuery) {
		List<SysUser> userList = baseService.list();
		return R.withResultObj(userList);
	}

	/**
	 * 保存用户
	 */
	@PostMapping()
	public R save(@RequestBody SysUserParam sysUserParam) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(sysUserParam.getUsername());
		sysUser.setEmail(sysUserParam.getEmail());
		sysUser.setMobile(sysUserParam.getMobile());

		sysUser.setCreateUserId(getCurrentUserId());
		baseService.encodePasswordAndSave(sysUser);
		sysUserRoleService.modifyUserRoles(sysUser.getUserId(), sysUserParam.getRoleIds());
		return R.ok("用户保存成功");
	}

	/**
	 * 修改用户
	 */
	@PutMapping
	public R update(@RequestBody SysUserParam sysUserParam) {
		SysUser sysUser = new SysUser();
		sysUser.setUserId(sysUserParam.getUserId());
		sysUser.setPassword(sysUserParam.getPassword());
		sysUser.setUsername(sysUserParam.getUsername());
		sysUser.setEmail(sysUserParam.getEmail());
		sysUser.setMobile(sysUserParam.getMobile());
		sysUser.setCreateUserId(getCurrentUserId());
		baseService.updateUser(sysUser);
		sysUserRoleService.modifyUserRoles(sysUser.getUserId(), sysUserParam.getRoleIds());
		return R.ok("用户修改成功");
	}
	
	/**
	 * 删除用户
	 */
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasAuthority('sys:user:delete')")
	public R delete(@PathVariable Long userId) {
		if(userId.equals(SysConst.SUPER_ADMIN_ID))
			return R.error("超管无法删除");
		if(userId.equals(getCurrentUserId()))
			return R.error("当前用户不能删除");
		baseService.removeById(userId);
		// 级联删除关联表
		sysUserRoleService.deleteByUserId(userId);
		return R.ok("删除成功");
	}

	/**
	 * 删除用户
	 */
	@DeleteMapping("/batch")
	@PreAuthorize("hasAuthority('sys:user:delete')")
	public R deleteBatch(@RequestBody Long[] userIds) {
		if(ArrayUtil.contains(userIds, SysConst.SUPER_ADMIN_ID))
			return R.error("超管无法删除");
		if(ArrayUtil.contains(userIds, getCurrentUserId()))
			return R.error("当前用户不能删除");
		baseService.removeByIds(Arrays.asList(userIds));

		return R.ok("删除成功");
	}

}

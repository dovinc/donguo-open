package pers.donguo.open.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.StrUtil;
import pers.donguo.open.modules.sys.entity.SysRole;
import pers.donguo.open.modules.sys.entity.SysRoleMenu;
import pers.donguo.open.modules.sys.params.SysRoleQuery;
import pers.donguo.open.modules.sys.repo.SysRoleDao;
import pers.donguo.open.modules.sys.repo.SysRoleMenuDao;
import pers.donguo.open.modules.sys.service.SysRoleService;

/**
 * <p>Title: SysRoleServiceImpl.java </p>
 * <p>Description: </p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
	@Autowired
	SysRoleMenuDao sysRoleMenuDao;
	
	public IPage<SysRole> pageList(Page<SysRole> page, SysRoleQuery roleQuery) {
		String roleName = roleQuery.getRoleName();
		IPage<SysRole> pageInfo = this.page(page,
			new QueryWrapper<SysRole>()
				.like(StrUtil.isNotBlank(roleName),"roleName", roleName)
		);
		return pageInfo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveRole(SysRole role, Long[] menuIds) {
		baseMapper.insert(role);
		Long roleId = role.getRoleId();
		int len = menuIds.length;
		for(int i = 0; i < len; i++) {
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setMenuId(menuIds[i]);
			roleMenu.setRoleId(roleId);
			sysRoleMenuDao.insert(roleMenu);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRole(SysRole role, Long[] menuIds) {
		baseMapper.updateById(role);
		sysRoleMenuDao.deleteByRoleId(role.getRoleId());
		Long roleId = role.getRoleId();
		int len = menuIds.length;
		for(int i = 0; i < len; i++) {
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setMenuId(menuIds[i]);
			roleMenu.setRoleId(roleId);
			sysRoleMenuDao.insert(roleMenu);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteRole(Long roleId) {
		baseMapper.deleteById(roleId);
		sysRoleMenuDao.deleteByRoleId(roleId);
	}
}

package pers.donguo.open.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.donguo.open.modules.sys.entity.SysRoleMenu;
import pers.donguo.open.modules.sys.repo.SysRoleMenuDao;
import pers.donguo.open.modules.sys.service.SysRoleMenuService;

/**
 * <p>Title: SysRoleMenuServiceImpl.java </p>
 * <p>Description: </p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {

	@Override
	public Long[] findReafMenuIdsByRoleIds(Long[] roleIds) {
		return baseMapper.findReafMenuIdsByRoleIds(roleIds);
	}
	
	
}

package pers.donguo.open.modules.sys.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.donguo.open.modules.sys.entity.SysUserRole;
import pers.donguo.open.modules.sys.repo.SysUserRoleDao;
import pers.donguo.open.modules.sys.service.SysUserRoleService;

/**
 * <p>Title: SysUserRoleServiceImpl.java </p>
 * <p>Description: </p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

	@Override
	public Long[] findRoleIdsByUserId(Long userId) {
		return baseMapper.findRoleIdsByUserId(userId);
	}
	
	@Override
	public Long[] findUserIdsByRoleId(Long roleId) {
		
		return baseMapper.findUserIdsByRoleId(roleId);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void modifyRoleUsers(Long roleId, Long[] userIds) {
		baseMapper.deleteByRoleId(roleId);
		this.insertBatch(userIds, new Long[] {roleId});
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void modifyUserRoles(Long userId, Long[] roleIds) {
		baseMapper.deleteByUserId(userId);
		this.insertBatch(new Long[] {userId}, roleIds);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertBatch(List<SysUserRole> userRoles) {
		if(userRoles instanceof RandomAccess) {
			// 实现随机访问接口则直接使用随机访问
			int len = userRoles.size();
			for (int i = 0; i < len; i++) {
				baseMapper.insert(userRoles.get(i));
			}
		}else {
			// 未实现随机访问接口则直接使用iterator 迭代器
			Iterator<SysUserRole> it = userRoles.iterator();
			while(it.hasNext()) {
				baseMapper.insert(it.next());
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertBatch(Long[] userIds, Long[] roleIds) {
			int userLen = userIds.length;
			int roleLen = roleIds.length;
			SysUserRole userRole = new SysUserRole();
			for (int i = 0; i < userLen; i++) {
				for (int j = 0; j < roleLen; j++) {
//					userRole.setId(null);
					userRole.setUserId(userIds[i]);
					userRole.setRoleId(roleIds[j]);
					baseMapper.insert(userRole);
				}
			}
		
	}

	@Override
	public void deleteByUserId(Long userId) {
		baseMapper.deleteByUserId(userId);
	}

	@Override
	public void deleteByRoleId(Long roleId) {
		baseMapper.deleteByRoleId(roleId);
	}

}

package pers.donguo.open.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import pers.donguo.open.common.exception.SysException;
import pers.donguo.open.common.utils.constant.SysConst;
import pers.donguo.open.config.security.MD5PasswordEncoder;
import pers.donguo.open.modules.sys.entity.SysUser;
import pers.donguo.open.modules.sys.params.SysUserQuery;
import pers.donguo.open.modules.sys.repo.SysUserDao;
import pers.donguo.open.modules.sys.service.SysUserService;

@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
	@Autowired
	MD5PasswordEncoder passwordEncoder;
	@Override
	public SysUser getUser(String username) {
		return baseMapper.findUserByUsername(username);
	}

	@Override
	public List<String> getPermissionList(Long userId) {
		if(userId == null) {
			throw new SysException("UserId cannot be null !");
		}
		String[] permissionArr;
		if(userId == SysConst.SUPER_ADMIN_ID) {
			permissionArr = baseMapper.findPermissions();
		}else {
			permissionArr = baseMapper.findPermissionsByUserId(userId);
		}
		if(permissionArr != null) {
			return Arrays.asList(permissionArr);
		} else {
			return new ArrayList<String>();
		}
	}

	@Override
	public IPage<SysUser> pageList(Page<SysUser> page, SysUserQuery userQuery) {
		String username = userQuery.getUsername();
		IPage<SysUser> pageInfo = this.page(page,
			new QueryWrapper<SysUser>()
				.like(StrUtil.isNotBlank(username),"username", username)
		);
		return pageInfo;
	}

	@Override
	public void encodePasswordAndSave(SysUser user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomUtil.randomString(10);
		user.setSalt(salt);
		user.setPassword(passwordEncoder.encode(user.getPassword()+salt));
		this.save(user);
	}

	@Override
	public void updateUser(SysUser user) {
		if(StrUtil.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			String password = passwordEncoder.encode(user.getPassword()+user.getSalt());
			user.setPassword(password);
		}
		this.updateById(user);
	}
	

}
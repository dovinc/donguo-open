package pers.donguo.open.modules.sys.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.donguo.open.common.utils.TokenUtil;
import pers.donguo.open.common.utils.constant.SysConst;
import pers.donguo.open.modules.sys.entity.SysUserToken;
import pers.donguo.open.modules.sys.repo.SysUserTokenDao;
import pers.donguo.open.modules.sys.service.SysUserTokenService;



@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserToken> implements SysUserTokenService {

	@Override
	public SysUserToken createUserToken(Long userId,String username) {
		Date now = new Date();
		//根据系统当前时间和 常量中token 的过期毫秒数，计算token 的过期时间
		Date expirationTime = new Date(now.getTime() + SysConst.TOKEN_EXPIRATION_TIME * 1000);
		//token工具类 生成token
		String token = TokenUtil.createToken();
		//判断userToken 表中是否已经生成过该用户的token
		SysUserToken SysUserToken = baseMapper.selectById(userId);

		boolean flag = SysUserToken == null;
		if(flag) {
			SysUserToken = new SysUserToken();
			SysUserToken.setUserId(userId);
			SysUserToken.setUsername(username);
		}
		SysUserToken.setToken(token);
		SysUserToken.setCreateTime(now);
		SysUserToken.setExpiredTime(expirationTime);
		//此userId 已有对应的token 则更新，没有则新增
		if(flag)
			this.save(SysUserToken);
		else
			this.updateById(SysUserToken);

		return SysUserToken;
	}
	
	@Override
	public SysUserToken findByToken(String token) {
		return baseMapper.findByToken(token);
	}

	@Override
	public void expired(Long userId) {
		//失效直接将对应userToken 删除
		removeById(userId);
	}
	
	@Override
	public void expired(String token) {
		//失效即为直接将对应userToken 删除
		baseMapper.deleteByToken(token);
	}

	@Override
	public void refreshExpiredTime(SysUserToken userToken) {
		Date now = new Date();
		Date expirationTime = new Date(now.getTime() + SysConst.TOKEN_EXPIRATION_TIME * 1000);
		userToken.setExpiredTime(expirationTime);
		baseMapper.updateById(userToken);
	}

}

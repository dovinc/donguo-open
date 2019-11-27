package pers.donguo.open.modules.sys.repo;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import pers.donguo.open.modules.sys.entity.SysUserToken;

/**
 * <p>Title: SysUserTokenDao.java </p>
 * <p>Description: UserToken 实体对应的 Dao 层</p>
 * @author Penguin
 * @date 2019年10月5日
 * @version 1.0
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserToken> {
	
	/**
	 * @title: findByToken
	 * @Description: 根据token（拥有唯一索引）字符串 查询实体
	 * @param token
	 * @return
	 */
	SysUserToken findByToken(String token);
	
	/**
	 * @title: deleteByToken
	 * @Description: 根据token（拥有唯一索引）字符串 删除
	 * @param token
	 * @return
	 */
	int deleteByToken(String token);
}

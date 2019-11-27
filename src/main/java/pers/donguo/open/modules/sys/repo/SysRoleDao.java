package pers.donguo.open.modules.sys.repo;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import pers.donguo.open.modules.sys.entity.SysRole;
/**
 * <p>Title: SysRoleDao.java </p>
 * <p>Description: 角色 Repo</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRole> {
	
}

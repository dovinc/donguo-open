package pers.donguo.open.modules.sys.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import pers.donguo.open.modules.sys.entity.SysMenu;

/**
 * <p>Title: SysMenuRepository.java</p>
 * <p>Description: 系统菜单 Repo</p>
 * @author Penguin
 * @date 2019年9月10日
 * @version 1.0
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenu> {
	/**
	 * @title: findMenuIdsByUserId
	 * @Description: 通过UserId 与SysUserRole
	 * @param UserId
	 * @return
	 */
	Long[] findMenuIdsByUserId(Long UserId);
	/**
	 * Desc：通过菜单父id 且in ids 查询对应 菜单列表
	 * 
	 * @param parentId
	 * @param ids
	 * @return
	 */
	List<SysMenu> findByParentIdAndMenuIdInAndTypeNotEquals(@Param("parentId") Long parentId, @Param("menuIds") Long[] menuIds, @Param("type") Integer type);

	/**
	 * Desc：通过菜单父id 查询对应 菜单列表
	 * 
	 * @param parentId
	 * @param ids
	 * @return
	 */
	List<SysMenu> findByParentId(Long parentId);

}

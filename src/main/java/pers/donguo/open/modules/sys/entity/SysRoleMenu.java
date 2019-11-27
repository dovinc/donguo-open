package pers.donguo.open.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * <p>Title: SysRoleMenu.java </p>
 * <p>Description: 角色菜单对应关系实体</p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
@Data
public class SysRoleMenu {
	@TableId
	private Long id;
	private Long roleId;
	private Long menuId;
}

package pers.donguo.open.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * ！！！未使用 -----后期需要使用部门功能时候进行扩展
 * <p>Title: SysRoleDept.java </p>
 * <p>Description: 角色部门关系实体</p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
@Data
public class SysRoleDept {
	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 角色Id
	 */
	private Long roleId;
	/**
	 *  部门Id
	 */
	private Long DeptId;
}

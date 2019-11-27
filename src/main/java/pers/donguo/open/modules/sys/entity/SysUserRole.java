/**
 * 
 */
package pers.donguo.open.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * <p>Title: SysUserRole.java </p>
 * <p>Description: SysUser 与 SysRole 多对多关系的中间表</p>
 * @author Penguin
 * @date 2019年11月10日
 * @version 1.0
 */
@Data
public class SysUserRole {
	@TableId
	private Long id;
	private Long userId;
	private Long roleId;
}

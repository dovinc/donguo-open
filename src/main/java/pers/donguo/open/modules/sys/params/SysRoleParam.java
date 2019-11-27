package pers.donguo.open.modules.sys.params;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>Title: SysRoleParam.java </p>
 * <p>Description: 角色  保存或更新时的参数类</p>
 * @author Penguin
 * @date 2019年11月9日
 * @version 1.0
 */
@Data
public class SysRoleParam implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private String roleName;

	private String description;

	private Long[] menuIds;
}

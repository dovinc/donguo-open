package pers.donguo.open.modules.sys.params;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * <p>Title: SysRoleModifyRoleUsersParam.java </p>
 * <p>Description: 角色分配用户时的参数类 </p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@Data
public class SysRoleModifyRoleUsersParam implements Serializable{
	private static final long serialVersionUID = 1L;
	@NotNull(message = "The param roleId cannot be null!")
	private Long roleId;
	@NotNull(message = "The param userIds cannot be null!")
	private Long[] userIds;
}

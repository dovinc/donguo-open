package pers.donguo.open.modules.sys.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import pers.donguo.open.modules.sys.entity.SysRole;

/**
 * <p>Title: RoleInfoDTO.java </p>
 * <p>Description: 权限详情信息传输对象【包含权限信息和其所对应的菜单id】</p>
 * @author Penguin
 * @date 2019年11月12日
 * @version 1.0
 */
@Data
public class RoleInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private String roleName;

	private String description;

	private Long createUserId;

	private Date createTime;
	
	private Long[] menuIds;
	
	public RoleInfoDTO(Long roleId, String roleName, String description, Long createUserId, Date createTime,
			Long[] menuIds) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.menuIds = menuIds;
	}
	
	public RoleInfoDTO(SysRole role) {
		this.roleId = role.getRoleId();
		this.roleName = role.getRoleName();
		this.description = role.getDescription();
		this.createUserId = role.getCreateUserId();
		this.createTime = role.getCreateTime();
	}
	
	public RoleInfoDTO(SysRole role, Long[] menuIds) {
		this.roleId = role.getRoleId();
		this.roleName = role.getRoleName();
		this.description = role.getDescription();
		this.createUserId = role.getCreateUserId();
		this.createTime = role.getCreateTime();
		this.menuIds = menuIds;
	}
	
}

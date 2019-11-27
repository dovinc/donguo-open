package pers.donguo.open.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class SysRole implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@TableId
	private Long roleId;

	/**
	 * 角色名
	 */
	private String roleName;
	
	/**
	 * 描述信息
	 */
	private String description;
	
	/**
	 * 创建者id
	 */
	private Long createUserId;
	/**
	 * 创建时间 
	 */
	private Date createTime;

}

package pers.donguo.open.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class SysAttr implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 属性表主键id
	 */
	@TableId
	private Long attrId;
	/**
	 * 属性名
	 */
	private String name;
	/**
	 * 属性值
	 */
	private String value;
	/**
	 * 属性描述
	 */
	private String description;
	/**
	 * 是否多级(Tree)
	 */
	private String multistage;
	/**
	 * 属性添加时间
	 */
	private Date createTime;

}

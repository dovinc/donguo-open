package pers.donguo.open.modules.sys.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>Title: SysAttrType.java </p>
 * <p>Description: 系统属性表</p>
 * @author Penguin
 * @date 2019年12月29日
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_attr_type")
public class SysAttrType implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 属性表主键id
	 */
	@TableId(value = "attr_type_id", type = IdType.AUTO)
	private Integer attrTypeId;

	/**
	 * 属性名
	 */
	private String title;

	/**
	 * 属性值
	 */
	private String code;

	/**
	 * 属性描述
	 */
	private String description;

	/**
	 * 是否多级，级联的0表示否
	 */
	private Integer multistage;

	/**
	 * 属性添加时间
	 */
	private LocalDateTime createTime;

}

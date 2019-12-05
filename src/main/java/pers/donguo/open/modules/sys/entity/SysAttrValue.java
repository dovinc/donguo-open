package pers.donguo.open.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class SysAttrValue implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 属性值表主键
	 */
	@TableId
	private Integer attrValueId;
	/**
	 * 所属部门的id，默认值为0 所有部门
	 */
	private Integer deptId;
	/**
	 * 
	 */
	private Integer level;
	/**
	 * 父级id 若无父级则为0
	 */
	private Integer parentId;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * view层显示值
	 */
	private String name;
	/**
	 * 属性值的类型
	 */
	private String typeName;
	/**
	 * 属性值类型编码对应属性表中的编码
	 */
	private String typeCode;
	/**
	 * 存库属性值
	 */
	private String value;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 排序
	 */
	private Integer orderNum;
	/**
	 * 创建时间
	 */
	private Date createTime;

}

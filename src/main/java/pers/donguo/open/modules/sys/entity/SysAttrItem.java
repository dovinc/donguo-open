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
 * <p>Title: SysAttrItem.java </p>
 * <p>Description: 属性键表attr表  所对应的属性值表</p>
 * @author Penguin
 * @date 2019年12月29日
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_attr_item")
public class SysAttrItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 属性值表主键
	 */
	@TableId(value = "attr_item_id", type = IdType.AUTO)
	private Integer attrItemId;

	/**
	 * 属性值类型编码对应属性表中的编码
	 */
	private String attrTypeCode;

	/**
	 * 级别
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
	private String title;

	/**
	 * 存库属性值
	 */
	private String value;

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
	private LocalDateTime createTime;

}

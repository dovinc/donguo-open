package pers.donguo.open.modules.sys.params;

import java.io.Serializable;

import lombok.Data;
import pers.donguo.open.common.base.ToConverter;
import pers.donguo.open.modules.sys.entity.SysAttrType;

/**
 * <p>Title: SysAttrTypeParam.java </p>
 * <p>Description: 系统属性  保存或更新时的参数类</p>
 * @author Penguin
 * @date 2019年12月1日
 * @version 1.0
 */
@Data
public class SysAttrTypeParam implements Serializable, ToConverter<SysAttrType> {
	private static final long serialVersionUID = 1L;

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

}

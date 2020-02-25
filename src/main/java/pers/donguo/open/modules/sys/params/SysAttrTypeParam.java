package pers.donguo.open.modules.sys.params;

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
public class SysAttrTypeParam implements ToConverter<SysAttrType> {

	private Long attrTypeId;
	/**
	 * 字典类型名
	 */
	private String title;
	/**
	 * 字典类型编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 是否多级(Tree)
	 */
	private String multistage;

}

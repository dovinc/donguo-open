package pers.donguo.open.modules.sys.params;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * <p>Title: SysAttrQuery.java </p>
 * <p>Description: 系统属性值 【与attr对应】 查询参数</p>
 * @author Penguin
 * @date 2019年12月1日
 * @version 1.0
 */
@Data
public class SysAttrItemQuery {
	@NotNull(message = "The param attrTypeCode cannot be null!")
	private String attrTypeCode;
}

/**
 * 
 */
package pers.donguo.open.common.base;

import pers.donguo.open.common.utils.BeanUtil;

/**
 * <p>Title: BaseConverter.java </p>
 * <p>Description: 实体【Bean,Param,DTO...】 的转换器</p>
 * @author Penguin
 * @date 2019年12月1日
 * @version 1.0
 */
public interface FromConverter<S> {
	default void convertFrom(S s) {
		BeanUtil.updateProperties(s, this);
	}
}

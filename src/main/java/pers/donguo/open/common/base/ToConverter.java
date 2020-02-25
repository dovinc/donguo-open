/**
 * 
 */
package pers.donguo.open.common.base;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

import org.springframework.lang.Nullable;

import pers.donguo.open.common.utils.BeanUtil;
import pers.donguo.open.common.utils.ReflectionUtil;

/**
 * <p>Title: BaseConverter.java </p>
 * <p>Description: 实体【Bean,Param,DTO...】 的转换器</p>
 * @author Penguin
 * @date 2019年12月1日
 * @version 1.0
 */
public interface ToConverter<T> {

	@SuppressWarnings("unchecked")
	default T convertTo() {
		// Get parameterized type
		ParameterizedType currentType = parameterizedType();

		// Assert not equal
		Objects.requireNonNull(currentType, "Cannot fetch actual type because parameterized type is null");
		// there is a problem, we can't get the Class object from genericity T, so we must do something to get it;
		Class<T> tClass = (Class<T>) currentType.getActualTypeArguments()[0];
		/*
		 * As follows Code is not allowed in Java,Because genericity erasure:
		 * 
		 * T t = new T();
		 * 
		 * So we must find other way to solve it;
		 */
		return BeanUtil.transformFrom(this, tClass);
	}

	default void update(T t) {
		BeanUtil.updateProperties(this, t);
	}

	@Nullable
	default ParameterizedType parameterizedType() {
		return ReflectionUtil.getParameterizedType(ToConverter.class, this.getClass());
	}
}

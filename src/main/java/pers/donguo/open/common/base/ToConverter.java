/**
 * 
 */
package pers.donguo.open.common.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import pers.donguo.open.common.utils.BeanUtil;

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
		ParameterizedType currentType = getParameterizedType(ToConverter.class, this.getClass());

		// Assert not equal
		Objects.requireNonNull(currentType, "Cannot fetch actual type because parameterized type is null");

		Class<T> tClass = (Class<T>) currentType.getActualTypeArguments()[0];

		return BeanUtil.transformFrom(this, tClass);
	}

	default void update(T t) {
		BeanUtil.updateProperties(this, t);
	}

	@Nullable
	public static ParameterizedType getParameterizedType1(@NonNull Class<?> interfaceType,
			Class<?> implementationClass) {
		Assert.notNull(interfaceType, "Interface type must not be null");
		Assert.isTrue(interfaceType.isInterface(), "The give type must be an interface");
		if (implementationClass == null) {
			// If the super class is Object parent then return null
			return null;
		}

		// Get parameterized type
		ParameterizedType currentType = getParameterizedType(interfaceType, implementationClass.getGenericInterfaces());

		if (currentType != null) {
			// return the current type
			return currentType;
		}

		Class<?> superclass = implementationClass.getSuperclass();

		return getParameterizedType1(interfaceType, superclass);
	}

	@Nullable
	public static ParameterizedType getParameterizedType(@NonNull Class<?> superType, Type... genericTypes) {
		Assert.notNull(superType, "Interface or super type must not be null");

		ParameterizedType currentType = null;

		for (Type genericType : genericTypes) {
			if (genericType instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) genericType;
				if (parameterizedType.getRawType().getTypeName().equals(superType.getTypeName())) {
					currentType = parameterizedType;
					break;
				}
			}
		}

		return currentType;
	}
}

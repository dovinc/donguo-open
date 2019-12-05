package pers.donguo.open.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Assert;
import pers.donguo.open.common.exception.SysBeanException;

public class BeanUtil {

	@Nullable
	public static <T> T transformFrom(@Nullable Object source, @NonNull Class<T> targetClass) {
		Assert.notNull(targetClass, "Target class must not be null");
		if (source == null) {
			return null;
		}
		try {
			T target = targetClass.newInstance();
			cn.hutool.core.bean.BeanUtil.copyProperties(source, target,
					CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
			return target;
		} catch (Exception e) {
			throw new SysBeanException("Bean transform exception cause: Faild to new" + targetClass.getName()
					+ " instance or copyProperties!");
		}
	}

	public static void updateProperties(@NonNull Object source, @NonNull Object target) {
		Assert.notNull(source, "Param source must not be null");
		Assert.notNull(target, "Param target object must not be null");
		try {
			cn.hutool.core.bean.BeanUtil.copyProperties(source, target,
					CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
		} catch (BeansException e) {
			throw new SysBeanException("Bean transform exception cause: Faild to copyProperties!");
		}
	}
}

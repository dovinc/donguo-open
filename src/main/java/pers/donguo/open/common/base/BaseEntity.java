package pers.donguo.open.common.base;

import java.util.Date;

import lombok.Data;

/**
 * <p>Title: BaseEntity.java </p>
 * <p>Description: 基础实体</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@Data
public abstract class BaseEntity {
	/**
	 * 创建者id
	 */
	protected Long createUserId;
	/**
	 * 创建时间
	 */
	protected Date createTime;
	/**
	 * 更新者id 
	 */
	protected Long updateUserId;
	/**
	 * 更新时间
	 */
	protected Date updateTime;
}

package pers.donguo.open.modules.sys.params.base;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * <p>Title: PageQuery.java </p>
 * <p>Description: 带页面参数的查询类【可单独使用也可以被继承】</p>
 * @author Penguin
 * @date 2019年11月9日
 * @version 1.0
 */
@Data
public class PageQuery {
	@NotNull(message = "Param page cannot be null!")
	@Min(value = 0, message = "Param page cannot be mimus!")
	private Integer page;
	
	@NotNull(message = "Param limit cannot be null!")
	@Max(value = 50, message = "Param limit cannot greater than 50!")
	@Min(value = 0, message = "Param limit cannot be minus!")
	private Integer limit;
}

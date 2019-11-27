package pers.donguo.open.modules.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pers.donguo.open.common.base.BaseEntity;
/**
 * <p>Title: SysUser.java </p>
 * <p>Description: 系统用户实体</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */

//@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "fieldHandler" }) // 配合fastJson使用防止JSON中对应字段为NULL报错
@Data
@AllArgsConstructor//全参构造器
@NoArgsConstructor//空参构造器
@EqualsAndHashCode(callSuper=false)
@ToString
public class SysUser extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机号
	 */
	private String mobile;
	
}

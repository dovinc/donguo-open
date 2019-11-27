package pers.donguo.open.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;


/**  
 * <p>Title: SysToken.java </p>
 * <p>Description: 系统后台用户token 对应的entity</p>
 * @author Penguin  
 * @date 2019年10月3日  
 * @version 1.0  
 */
@Data
public class SysUserToken implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 *  用户Id 由于每个用户有唯一的token 所以直接使用其作为token 的主键
	 */
	@TableId(type = IdType.INPUT)
	private Long userId;
	/**
	 *  用户名
	 */
	private String username;

	/**
	 * token字符串
	 */
	private String token;
	
	/**
	 * token 创建时间
	 */
	private Date createTime;
	
	/**
	 * token 过期时间
	 */
	private Date expiredTime;
}

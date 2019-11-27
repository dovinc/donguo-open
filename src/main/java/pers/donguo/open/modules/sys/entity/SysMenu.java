package pers.donguo.open.modules.sys.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * <p>Title: SysMenu.java </p>
 * <p>Description: 系统菜单实体</p>
 * @author Penguin
 * @date 2019年10月5日
 * @version 1.0
 */
//@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "fieldHandler" }) // 配合fastJson使用防止JSON中对应字段为NULL报错
@Data
public class SysMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键  菜单ID
	 */
	@TableId
	private Long menuId;

	/**
	 * 父菜单ID，一级菜单为0L
	 */
	private Long parentId;

	/**
	 * 父菜单名
	 */
	@TableField(exist=false)
	private String parentName;

	/**
	 * 菜单名
	 */
	private String name;

	/**
	 * 菜单URL 【与前端项目中文件相对路径所对应】
	 */
	private String url;
	
	/**
	 * 路由路径  【与vueRouter 添加的路由path 属性对应】
	 */
	private String path;

	/**
	 * 授权信息（Spring Security 对应的权限字符串/若为Shiro 则也为其对应），以','分隔（!!业务逻辑中是使用','拆分的）
	 */
	private String permissions;

	/**
	 * 类型 0：目录 1：菜单 2：按钮 （系统中拥有对应常量MenuConst.Type）
	 */
	private Integer type;

	/**
	 * 菜单图标【前端使用svg-icon加载】
	 */
	private String icon;

	/**
	 * 排序数
	 */
	private Integer orderNum;

	/**
	 * 子菜单列表
	 */
	@TableField(exist=false)
	private List<SysMenu> menuList;

}

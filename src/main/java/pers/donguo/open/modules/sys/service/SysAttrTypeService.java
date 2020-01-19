package pers.donguo.open.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import pers.donguo.open.modules.sys.entity.SysAttrType;
import pers.donguo.open.modules.sys.params.SysAttrTypeQuery;

/**
 * <p>Title: SysAttrTypeService.java </p>
 * <p>Description: 系统属性类型service层</p>
 * @author Penguin
 * @date 2019年12月29日
 * @version 1.0
 */
public interface SysAttrTypeService extends IService<SysAttrType> {
	/**
	 * @title: pageList
	 * @Description: SysAttrTypeService's pageList | 动态查询 分页列表
	 * @param page
	 * @param attrTypeQuery
	 * @return
	 */
	IPage<SysAttrType> pageList(Page<SysAttrType> page, SysAttrTypeQuery attrTypeQuery);
}

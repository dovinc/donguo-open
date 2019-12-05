package pers.donguo.open.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import pers.donguo.open.modules.sys.entity.SysAttrValue;
import pers.donguo.open.modules.sys.params.SysAttrValueQuery;

public interface SysAttrValueService extends IService<SysAttrValue> {
	/**
	 * @title: pageList
	 * @Description: SysAttrValueService's pageList |  动态查询 分页列表
	 * @param page
	 * @param attrValueQuery
	 * @return
	 */
	IPage<SysAttrValue> pageList(Page<SysAttrValue> page, SysAttrValueQuery attrValueQuery);
}

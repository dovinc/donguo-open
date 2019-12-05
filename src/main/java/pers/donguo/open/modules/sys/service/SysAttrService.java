package pers.donguo.open.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import pers.donguo.open.modules.sys.entity.SysAttr;
import pers.donguo.open.modules.sys.params.SysAttrQuery;

public interface SysAttrService extends IService<SysAttr> {
	/**
	 * @title: pageList
	 * @Description: SysAttrService's pageList | 动态查询 分页列表
	 * @param page
	 * @param attrQuery
	 * @return
	 */
	IPage<SysAttr> pageList(Page<SysAttr> page, SysAttrQuery attrQuery);
}

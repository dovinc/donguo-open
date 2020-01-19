package pers.donguo.open.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import pers.donguo.open.modules.sys.entity.SysAttrItem;
import pers.donguo.open.modules.sys.params.SysAttrItemQuery;

/**
 * <p>Title: SysAttrItemService.java </p>
 * <p>Description: 属性键表attr表  所对应的属性值表 服务类</p>
 * @author Penguin
 * @date 2019年12月29日
 * @version 1.0
 */
public interface SysAttrItemService extends IService<SysAttrItem> {
	/**
	 * @title: pageList
	 * @Description: SysAttrValueService's pageList |  动态查询 分页列表
	 * @param page
	 * @param attrValueQuery
	 * @return
	 */
	IPage<SysAttrItem> pageList(Page<SysAttrItem> page, SysAttrItemQuery attrItemQuery);
}

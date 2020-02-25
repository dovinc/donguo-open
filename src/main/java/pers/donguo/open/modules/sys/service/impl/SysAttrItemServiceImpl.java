package pers.donguo.open.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.StrUtil;
import pers.donguo.open.modules.sys.entity.SysAttrItem;
import pers.donguo.open.modules.sys.params.SysAttrItemQuery;
import pers.donguo.open.modules.sys.repo.SysAttrItemDao;
import pers.donguo.open.modules.sys.service.SysAttrItemService;

@Service("attrItemService")
public class SysAttrItemServiceImpl extends ServiceImpl<SysAttrItemDao, SysAttrItem> implements SysAttrItemService {
	@Override
	public IPage<SysAttrItem> pageList(Page<SysAttrItem> page, SysAttrItemQuery attrItemQuery) {
		IPage<SysAttrItem> pageInfo = this.page(page,
				new QueryWrapper<SysAttrItem>().eq(StrUtil.isNotBlank(attrItemQuery.getAttrTypeCode()),
						"attr_type_code", attrItemQuery.getAttrTypeCode()));
		return pageInfo;
	}
}

package pers.donguo.open.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.donguo.open.modules.sys.entity.SysAttrValue;
import pers.donguo.open.modules.sys.params.SysAttrValueQuery;
import pers.donguo.open.modules.sys.repo.SysAttrValueDao;
import pers.donguo.open.modules.sys.service.SysAttrValueService;

@Service("sysAttrValueService")
public class SysAttrValueServiceImpl extends ServiceImpl<SysAttrValueDao, SysAttrValue> implements SysAttrValueService {

	@Override
	public IPage<SysAttrValue> pageList(Page<SysAttrValue> page, SysAttrValueQuery attrValueQuery) {
		IPage<SysAttrValue> pageInfo = this.page(page);
		return pageInfo;
	}

}

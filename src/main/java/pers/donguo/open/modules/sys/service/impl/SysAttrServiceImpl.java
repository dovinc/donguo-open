package pers.donguo.open.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.donguo.open.modules.sys.entity.SysAttr;
import pers.donguo.open.modules.sys.params.SysAttrQuery;
import pers.donguo.open.modules.sys.repo.SysAttrDao;
import pers.donguo.open.modules.sys.service.SysAttrService;

@Service("sysAttrService")
public class SysAttrServiceImpl extends ServiceImpl<SysAttrDao, SysAttr> implements SysAttrService {
	@Override
	public IPage<SysAttr> pageList(Page<SysAttr> page, SysAttrQuery attrQuery) {
		IPage<SysAttr> pageInfo = this.page(page); // new QueryWrapper<SysRole>().like(StrUtil.isNotBlank(roleName), "roleName", roleName)
		return pageInfo;
	}
}

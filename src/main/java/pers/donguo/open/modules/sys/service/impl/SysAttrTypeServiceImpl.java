package pers.donguo.open.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.donguo.open.modules.sys.entity.SysAttrType;
import pers.donguo.open.modules.sys.params.SysAttrTypeQuery;
import pers.donguo.open.modules.sys.repo.SysAttrTypeDao;
import pers.donguo.open.modules.sys.service.SysAttrTypeService;

/**
 * <p>Title: SysAttrTypeServiceImpl.java </p>
 * <p>Description: 系统属性表 服务实现类</p>
 * @author Penguin
 * @date 2019年12月29日
 * @version 1.0
 */
@Service
public class SysAttrTypeServiceImpl extends ServiceImpl<SysAttrTypeDao, SysAttrType> implements SysAttrTypeService {
	@Override
	public IPage<SysAttrType> pageList(Page<SysAttrType> page, SysAttrTypeQuery attrQuery) {
		IPage<SysAttrType> pageInfo = this.page(page); // new QueryWrapper<SysRole>().like(StrUtil.isNotBlank(roleName), "roleName", roleName)
		return pageInfo;
	}
}

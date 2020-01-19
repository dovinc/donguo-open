package pers.donguo.open.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.donguo.open.modules.sys.entity.SysAttrItem;
import pers.donguo.open.modules.sys.params.SysAttrItemQuery;
import pers.donguo.open.modules.sys.repo.SysAttrItemDao;
import pers.donguo.open.modules.sys.service.SysAttrItemService;

/**
 * <p>
 * 属性键表attr表  所对应的属性值表 服务实现类
 * </p>
 *
 * @author donguo赵东jobob
 * @since 2019-12-29
 */
@Service("attrItemService")
public class SysAttrItemServiceImpl extends ServiceImpl<SysAttrItemDao, SysAttrItem> implements SysAttrItemService {
	@Override
	public IPage<SysAttrItem> pageList(Page<SysAttrItem> page, SysAttrItemQuery attrValueQuery) {
		IPage<SysAttrItem> pageInfo = this.page(page);
		return pageInfo;
	}
}

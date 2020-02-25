package pers.donguo.open.modules.sys.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.StrUtil;
import pers.donguo.open.common.base.BasicController;
import pers.donguo.open.common.utils.R;
import pers.donguo.open.modules.sys.dto.base.PageDTO;
import pers.donguo.open.modules.sys.entity.SysAttrItem;
import pers.donguo.open.modules.sys.params.SysAttrItemQuery;
import pers.donguo.open.modules.sys.params.base.PageQuery;
import pers.donguo.open.modules.sys.service.SysAttrItemService;

/**
 * <p>Title: SysAttrItemController.java </p>
 * <p>Description: 属性值项表 前端控制器</p>
 * @author Penguin
 * @date 2019年12月29日
 * @version 1.0
 */
@RestController
@RequestMapping("/sys/attr-items")
public class SysAttrItemController extends BasicController<SysAttrItem, SysAttrItemService> {

	/**
	 * 列表
	 */
	@GetMapping("/all")
//	@RequiresPermissions("sys:attrvalues:get:list")
	public R all(@Validated SysAttrItemQuery attrItemQuery) {
		List<SysAttrItem> all = baseService
				.list(new QueryWrapper<SysAttrItem>().eq(StrUtil.isNotBlank(attrItemQuery.getAttrTypeCode()),
						"attr_type_code", attrItemQuery.getAttrTypeCode()));
		return R.withResultObj(all);
	}

	/**
	 * 列表
	 */
	@GetMapping()
//	@RequiresPermissions("sys:attrvalues:get:list")
	public R list(@Validated PageQuery pageQuery, @Validated SysAttrItemQuery attrItemQuery) {
		Integer page = pageQuery.getPage();
		Integer limit = pageQuery.getLimit();
		IPage<SysAttrItem> pageInfo = baseService.pageList(new Page<SysAttrItem>(page, limit), attrItemQuery);
		PageDTO pageDTO = new PageDTO(pageInfo);
		return R.withResultObj(pageDTO);
	}

	/**
	 * 信息
	 */
	@GetMapping("/{attrItemId}")
//	@RequiresPermissions("sys:attrvalues:get")
	public R info(@PathVariable("attrItemId") Integer attrItemId) {
		SysAttrItem sysAttrItem = baseService.getById(attrItemId);
		return R.withResultObj(sysAttrItem);
	}

	/**
	 * 保存
	 */
	@PostMapping()
//	@RequiresPermissions("sys:attrvalues:post")
	public R save(SysAttrItem sysAttrItem) {
		baseService.save(sysAttrItem);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping()
//	@RequiresPermissions("sys:attrvalues:put")
	public R update(SysAttrItem sysAttrItem) {
		baseService.updateById(sysAttrItem);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{attrItemId}")
//	@RequiresPermissions("sys:attrs:delete")
	public R delete(@PathVariable Long attrItemId) {
		baseService.removeById(attrItemId);
		return R.ok("删除成功");
	}

	/**
	 * 批量删除
	 */
	@DeleteMapping("/batch")
//	@RequiresPermissions("sys:attrs:delete")
	public R deleteBatch(Long[] attrItemIds) {
		baseService.removeByIds(Arrays.asList(attrItemIds));
		return R.ok();
	}

}

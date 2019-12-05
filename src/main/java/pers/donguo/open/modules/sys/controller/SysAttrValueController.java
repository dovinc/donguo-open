package pers.donguo.open.modules.sys.controller;

import java.util.Arrays;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import pers.donguo.open.common.base.BasicController;
import pers.donguo.open.common.utils.R;
import pers.donguo.open.modules.sys.dto.base.PageDTO;
import pers.donguo.open.modules.sys.entity.SysAttrValue;
import pers.donguo.open.modules.sys.params.SysAttrValueQuery;
import pers.donguo.open.modules.sys.params.base.PageQuery;
import pers.donguo.open.modules.sys.service.SysAttrValueService;

@RestController
@RequestMapping("sys/attr-values")
public class SysAttrValueController extends BasicController<SysAttrValue, SysAttrValueService> {
	/**
	 * 列表
	 */
	@GetMapping()
//	@RequiresPermissions("sys:attrvalues:get:list")
	public R list(@Validated PageQuery pageQuery, @Validated SysAttrValueQuery attrValueQuery) {
		Integer page = pageQuery.getPage();
		Integer limit = pageQuery.getLimit();
		IPage<SysAttrValue> pageInfo = baseService.pageList(new Page<SysAttrValue>(page, limit), attrValueQuery);
		PageDTO pageDTO = new PageDTO(pageInfo);
		return R.withResultObj(pageDTO);
	}

	/**
	 * 信息
	 */
	@GetMapping("/{attrValueId}")
//	@RequiresPermissions("sys:attrvalues:get")
	public R info(@PathVariable("attrValueId") Integer attrValueId) {
		SysAttrValue sysAttrValue = baseService.getById(attrValueId);

		return R.withResultObj(sysAttrValue);
	}

	/**
	 * 保存
	 */
	@PostMapping()
//	@RequiresPermissions("sys:attrvalues:post")
	public R save(@RequestBody SysAttrValue sysAttrValue) {
		baseService.save(sysAttrValue);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping()
//	@RequiresPermissions("sys:attrvalues:put")
	public R update(@RequestBody SysAttrValue sysAttrValue) {
		baseService.updateById(sysAttrValue);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping()
//	@RequiresPermissions("sys:attrvalues:delete")
	public R delete(@RequestBody Integer[] attrValueIds) {
		baseService.removeByIds(Arrays.asList(attrValueIds));
		return R.ok();
	}

}

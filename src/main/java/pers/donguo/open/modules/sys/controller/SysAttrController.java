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
import pers.donguo.open.modules.sys.entity.SysAttr;
import pers.donguo.open.modules.sys.params.SysAttrParam;
import pers.donguo.open.modules.sys.params.SysAttrQuery;
import pers.donguo.open.modules.sys.params.base.PageQuery;
import pers.donguo.open.modules.sys.service.SysAttrService;

@RestController
@RequestMapping("sys/attrs")
public class SysAttrController extends BasicController<SysAttr, SysAttrService> {
	/**
	 * 列表
	 */
	@GetMapping()
//	@RequiresPermissions("sys:attrs:get:list")
	public R list(@Validated PageQuery pageQuery, @Validated SysAttrQuery sysAttrQuery) {
		Integer page = pageQuery.getPage();
		Integer limit = pageQuery.getLimit();
		IPage<SysAttr> pageInfo = baseService.pageList(new Page<SysAttr>(page, limit), sysAttrQuery);
		PageDTO pageDTO = new PageDTO(pageInfo);

		return R.withResultObj(pageDTO);
	}

	/**
	 * 信息
	 */
	@GetMapping("/{attrId}")
//	@RequiresPermissions("sys:attrs:get")
	public R info(@PathVariable("attrId") Long attrId) {
		SysAttr attr = baseService.getById(attrId);
		return R.withResultObj(attr);
	}

	/**
	 * 保存
	 */
	@PostMapping()
//	@RequiresPermissions("sys:attrs:post")
	public R save(@RequestBody SysAttrParam attrParam) {
		baseService.save(attrParam.convertTo());
		return R.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping()
//	@RequiresPermissions("sys:attrs:put")
	public R update(SysAttrParam attrParam) {

		baseService.updateById(attrParam.convertTo());
		return R.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping()
//	@RequiresPermissions("sys:attrs:delete")
	public R delete(@RequestBody Long[] attrIds) {
		baseService.removeByIds(Arrays.asList(attrIds));
		return R.ok();
	}
}

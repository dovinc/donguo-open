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
import pers.donguo.open.modules.sys.entity.SysAttrType;
import pers.donguo.open.modules.sys.params.SysAttrTypeParam;
import pers.donguo.open.modules.sys.params.SysAttrTypeQuery;
import pers.donguo.open.modules.sys.params.base.PageQuery;
import pers.donguo.open.modules.sys.service.SysAttrTypeService;

/**
 * <p>Title: SysAttrTypeController.java </p>
 * <p>Description: 系统属性表 前端控制器</p>
 * @author Penguin
 * @date 2019年12月29日
 * @version 1.0
 */
@RestController
@RequestMapping("/sys/attr-types")
public class SysAttrTypeController extends BasicController<SysAttrType, SysAttrTypeService> {
	/**
	 * 列表
	 */
	@GetMapping()
//	@RequiresPermissions("sys:attrs:get:list")
	public R list(@Validated PageQuery pageQuery, @Validated SysAttrTypeQuery attrTypeQuery) {
		Integer page = pageQuery.getPage();
		Integer limit = pageQuery.getLimit();
		IPage<SysAttrType> pageInfo = baseService.pageList(new Page<SysAttrType>(page, limit), attrTypeQuery);
		PageDTO pageDTO = new PageDTO(pageInfo);

		return R.withResultObj(pageDTO);
	}

	/**
	 * 信息
	 */
	@GetMapping("/{attrTypeId}")
//	@RequiresPermissions("sys:attrs:get")
	public R info(@PathVariable("attrTypeId") Long attrTypeId) {
		SysAttrType attrType = baseService.getById(attrTypeId);
		return R.withResultObj(attrType);
	}

	/**
	 * 保存
	 */
	@PostMapping()
//	@RequiresPermissions("sys:attrs:post")
	public R save(@RequestBody SysAttrTypeParam attrTypeParam) {
		baseService.save(attrTypeParam.convertTo());
		return R.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping()
//	@RequiresPermissions("sys:attrs:put")
	public R update(SysAttrTypeParam attrTypeParam) {
		baseService.updateById(attrTypeParam.convertTo());
		return R.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping()
//	@RequiresPermissions("sys:attrs:delete")
	public R delete(@RequestBody Long[] attrTypeIds) {
		baseService.removeByIds(Arrays.asList(attrTypeIds));
		return R.ok();
	}
}

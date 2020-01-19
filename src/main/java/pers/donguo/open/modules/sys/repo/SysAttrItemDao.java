package pers.donguo.open.modules.sys.repo;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import pers.donguo.open.modules.sys.entity.SysAttrItem;

/**
 * <p>Title: SysAttrItemDao.java </p>
 * <p>Description: 属性键表attr表  所对应的属性值表 Mapper 接口</p>
 * @author Penguin
 * @date 2019年12月29日
 * @version 1.0
 */
@Mapper
public interface SysAttrItemDao extends BaseMapper<SysAttrItem> {

}

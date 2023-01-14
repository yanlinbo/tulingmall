package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.dto.RelationAttrInfoDto;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {

    public Page list(Long cid, String type, Integer pageNum, Integer pageSize);

    public Boolean create(PmsProductAttribute pmsProductAttribute);

    public int delete(List<Long> ids);

    List<RelationAttrInfoDto> getRelationAttrInfoByCid(Long cid);
}

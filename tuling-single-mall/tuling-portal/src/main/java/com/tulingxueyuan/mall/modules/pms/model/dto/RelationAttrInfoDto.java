package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="RelationAttrInfoDto商品分类和商品属性的关联数据",description = "商品分类和商品属性关联已保存数据的初始化")

public class RelationAttrInfoDto {

    /**
     * 商品类型ID
     */
    private Long productCategoryId;

    /**
     * 商品属性ID
     */
    private Long productAttributeId;
}

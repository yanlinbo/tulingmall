package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="ProductAttributeCategoryDto帅选数据的传输对象",description = "用于商品的分类-刷选商品属性下拉级联")
public class ProductAttributeCategoryDto {

    /**
     * 商品类型的ID
     */
    private Long id;

    /**
     * 商品类型的名称
     */
    private String name;

    /**
     * 商品属性的二级级联
     */
    private List<PmsProductAttribute> children;
}

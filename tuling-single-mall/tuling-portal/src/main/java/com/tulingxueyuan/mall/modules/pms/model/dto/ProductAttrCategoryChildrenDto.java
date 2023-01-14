package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="ProductAttrCategoryChildrenDto商品一级分类和二级分类级联传输对象",description = "用于商品列表-商品分类下拉级联")
public class ProductAttrCategoryChildrenDto {

    /**
     * 商品类型的ID
     */
    private Long id;

    /**
     * 商品类型的名称
     */
    private String name;

    /**
     * 商品的二级分类
     */
    private List<PmsProductCategory> children;
}

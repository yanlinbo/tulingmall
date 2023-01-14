package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeValue;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value="产品详情Dto",description = "产品详情Dto")
public class ProductDetailDto extends PmsProduct {

    //商品属性相关
    private List<PmsProductAttributeValueDto> productAttributeValueDtoList;
    //商品sku库存信息·
    private List<PmsSkuStock> skuStockList;
}

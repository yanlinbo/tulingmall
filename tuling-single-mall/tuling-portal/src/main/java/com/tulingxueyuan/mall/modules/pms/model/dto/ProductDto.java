package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value="商品对象描述",description = "商品对象描述")
public class ProductDto {

    private Long id;

    private String name;

    private String pic;
    @ApiModelProperty(value = "促销价格")
    private BigDecimal promotionPrice;
    @ApiModelProperty(value = "市场价格")
    private BigDecimal price;
    @ApiModelProperty(value = "副标题")
    private String subTitle;
}

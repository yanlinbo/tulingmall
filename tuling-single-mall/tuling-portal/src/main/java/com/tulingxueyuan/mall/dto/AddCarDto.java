package com.tulingxueyuan.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="添加购物车数据传输对象",description = "添加购物车")
public class AddCarDto {

    private Long productId;

    private Long productSkuId;

    private Integer quantity;
}

package com.tulingxueyuan.mall.dto;

import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value="购物车和库存数据传输对象",description = "购物车和库存")
public class CartItemStockDto extends OmsCartItem { //OmsOrderItem

    @ApiModelProperty("真实库存（实际库存-锁定库存）")
    private Integer stock;

}

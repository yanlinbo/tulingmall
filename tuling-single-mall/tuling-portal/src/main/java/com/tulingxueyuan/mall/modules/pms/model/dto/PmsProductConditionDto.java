package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="商品帅选的条件",description = "商品帅选的条件")
public class PmsProductConditionDto {

    private String keyword;

    private Integer pageNum;

    private Integer pageSize;

    @ApiModelProperty(value = "货号")
    private String productSn;

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    private Integer publishStatus;

    @ApiModelProperty(value = "审核状态：0->未审核；1->审核通过")
    private Integer verifyStatus;

    private Long brandId;

    private Long productCategoryId;
}

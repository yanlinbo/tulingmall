package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeValue;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="商品属性sku数据传输对象",description = "用于商品详情展示")
public class PmsProductAttributeValueDto extends PmsProductAttributeValue {
    private String attrName;
}

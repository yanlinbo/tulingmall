package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="商品菜单数据传输对象",description = "商品菜单")
public class HomeMenusDto {

    private Long id;

    private String name;

    private List<ProductDto> productList;
}

package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="商品分类的数据传输对象",description = "用于商品分类添加、修改")
public class PmsProductCategoryDto extends PmsProductCategory {

    private List<Long> pmsProductAttributeIdList;

}

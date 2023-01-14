package com.tulingxueyuan.mall.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductDetailDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="ProductController",description = "产品详情接口")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    PmsProductService pmsProductService;

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    @RequestMapping("/detail/{id}")
    public CommonResult getProductDetail(@PathVariable Long id){
        ProductDetailDto productDetailDto = pmsProductService.getProductDetail(id);
        return CommonResult.success(productDetailDto);
    }
}

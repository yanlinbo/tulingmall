package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCategoryDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
@RestController
@RequestMapping("/pms/pmsProductAttributeCategory")
public class PmsProductAttributeCategoryController {

    @Autowired
    public PmsProductAttributeCategoryService pmsProductAttributeCategoryService;

    @ApiOperation("查询商品属性类型列表")
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductAttributeCategory>> findPmsProductCategoryList(
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize
    ){

        Page<PmsProductAttributeCategory> page = pmsProductAttributeCategoryService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiOperation("商品属性类型添加或更新")
    @RequestMapping(value="/createOrUpdate}",method= RequestMethod.POST)
    public CommonResult<Boolean> create(@RequestBody PmsProductAttributeCategory pmsProductAttributeCategory) throws Exception {
        if(pmsProductAttributeCategory == null ){
            //这里需要自定义业务异常
            throw new Exception("无添加对象");
        }
        boolean result = pmsProductAttributeCategoryService.saveOrUpdate(pmsProductAttributeCategory);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据id查询商品属性类型")
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public CommonResult<PmsProductAttributeCategory> getById(@PathVariable Long id) {

        PmsProductAttributeCategory pmsProductAttributeCategory = pmsProductAttributeCategoryService.getById(id);
        return CommonResult.success(pmsProductAttributeCategory);
    }

    @ApiOperation("商品属性类型删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteById(@PathVariable Long id){

        boolean result = pmsProductAttributeCategoryService.removeById(id);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("帅选商品分类-商品属性的级联")
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    public CommonResult getListWithAttr(){

        List<ProductAttributeCategoryDto> result = pmsProductAttributeCategoryService.getListWithAttr();
        return CommonResult.success(result);
    }

}


package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDto;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCategoryDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
@RestController
@RequestMapping("/pms/pmsProductCategory")
public class PmsProductCategoryController {

    @Autowired
    public PmsProductCategoryService pmsProductCategoryService;

    @ApiOperation("查询商品分类列表")
    @RequestMapping(value="/list/{parentId}",method= RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductCategory>> list(
            @PathVariable Long parentId,
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize
    ){

        Page<PmsProductCategory> page = pmsProductCategoryService.list( parentId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

//    @ApiOperation("商品分类添加或更新")
//    @RequestMapping(value="/createOrUpdate",method= RequestMethod.POST)
//    public CommonResult<Boolean> createOrUpdate(@RequestBody PmsProductCategory pmsProductCategory) throws Exception {
//        if(pmsProductCategory == null ){
//            //这里需要自定义业务异常
//            throw new Exception("无添加对象");
//        }
//        boolean result = pmsProductCategoryService.saveOrUpdate(pmsProductCategory);
//        if(result){
//            return CommonResult.success(result);
//        }else{
//            return CommonResult.failed();
//        }
//    }

    @ApiOperation("商品分类添加")
    @RequestMapping(value="/create",method= RequestMethod.POST)
    public CommonResult<Boolean> create(@RequestBody PmsProductCategoryDto productCategoryDto) throws Exception {
        if(productCategoryDto == null ){
            //这里需要自定义业务异常
            throw new Exception("无添加对象");
        }
        boolean result = pmsProductCategoryService.customSave(productCategoryDto);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("商品分类更新")
    @RequestMapping(value="/update/{id}",method= RequestMethod.POST)
    public CommonResult<Boolean> update(@RequestBody PmsProductCategoryDto productCategoryDto) throws Exception {
        if(productCategoryDto == null ){
            //这里需要自定义业务异常
            throw new Exception("无添加对象");
        }
        boolean result = pmsProductCategoryService.update(productCategoryDto);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据id查询商品分类")
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public CommonResult<PmsProductCategory> getById(@PathVariable Long id) {

        PmsProductCategory pmsProductCategory = pmsProductCategoryService.getById(id);
        return CommonResult.success(pmsProductCategory);
    }

    @ApiOperation("商品分类删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteById(@PathVariable Long id){

        boolean result = pmsProductCategoryService.removeById(id);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("获取商品的一级分类和二级分类的下拉级联数据")
    @RequestMapping(value="/list/withChildren",method= RequestMethod.GET)
    public CommonResult<List<ProductAttributeCategoryDto>> getWithChildren(){

        List<ProductAttributeCategoryDto> children = pmsProductCategoryService.getWithChildren();
        return CommonResult.success(children);
    }

}


package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.RelationAttrInfoDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
@RestController
@RequestMapping("/pms/pmsProductAttribute")
public class PmsProductAttributeController {

    @Autowired
    public PmsProductAttributeService pmsProductAttributeService;

    @ApiOperation("查询商品属性列表")
    @RequestMapping(value="/list/{cid}",method= RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductAttribute>> list(
            @PathVariable Long cid,
            @RequestParam(value="type") String type,
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize
    ){

        Page<PmsProductAttribute> page = pmsProductAttributeService.list( cid, type, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("商品属性添加")
    @RequestMapping(value="/create}",method= RequestMethod.POST)
    public CommonResult<Boolean> create(@RequestBody PmsProductAttribute pmsProductAttribute) throws Exception {
        if(pmsProductAttribute == null ){
            //这里需要自定义业务异常
            throw new Exception("无添加对象");
        }
        boolean result = pmsProductAttributeService.create(pmsProductAttribute);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("商品属性更新")
    @RequestMapping(value="/update}",method= RequestMethod.POST)
    public CommonResult<Boolean> update(@RequestBody PmsProductAttribute pmsProductAttribute) throws Exception {
        if(pmsProductAttribute == null){
            //这里需要自定义业务异常
            throw new Exception("无添加对象");
        }
        boolean result = pmsProductAttributeService.saveOrUpdate(pmsProductAttribute);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据id查询商品属性")
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public CommonResult<PmsProductAttribute> getById(@PathVariable Long id) {

        PmsProductAttribute pmsProductAttribute = pmsProductAttributeService.getById(id);
        return CommonResult.success(pmsProductAttribute);
    }

    @ApiOperation("商品属性删除")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public CommonResult delete(@RequestBody List<Long> ids){

        int result = pmsProductAttributeService.delete(ids);
        if (result > 0) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }

    }


    @ApiOperation("根据商品分类id后去关联的属性")
    @RequestMapping(value = "/AttrInfo/{cid}", method = RequestMethod.GET)
    public CommonResult getRelationAttrInfoByCid(@PathVariable Long cid){

        List<RelationAttrInfoDto> result = pmsProductAttributeService.getRelationAttrInfoByCid(cid);
        return CommonResult.success(result);
    }


}


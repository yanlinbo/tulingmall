package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductConditionDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
@RestController
@RequestMapping("/pms/pmsProduct")
public class PmsProductController {

    @Autowired
    private PmsProductService pmsProductService;

    /**
     * 查询商品列表
     * @return
     */
    @ApiOperation("商品列表查询")
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public CommonResult<CommonPage<PmsProduct>> list(PmsProductConditionDto condition){

        Page<PmsProduct> page = pmsProductService.page(condition);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiOperation("商品逻辑删除")
    @RequestMapping(value="/update/deleteStatus",method= RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids){

        boolean result = pmsProductService.removeByIds(ids);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }

    }

    @ApiOperation("更新是否新品")
    @RequestMapping(value="/update/newStatus",method= RequestMethod.POST)
    public CommonResult updateNewStatus(@RequestParam("ids") List<Long> ids,
                               @RequestParam("newStatus")Integer newStatus){

        boolean result = pmsProductService.updateStatus(newStatus,ids,PmsProduct::getNewStatus);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("更新是否推荐")
    @RequestMapping(value="/update/recommendStatus",method= RequestMethod.POST)
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                               @RequestParam("recommendStatus")Integer recommendStatus){

        boolean result = pmsProductService.updateStatus(recommendStatus,ids,PmsProduct::getRecommandStatus);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("更新是否上架")
    @RequestMapping(value="/update/publishStatus",method= RequestMethod.POST)
    public CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                               @RequestParam("publishStatus")Integer publishStatus){

        boolean result = pmsProductService.updateStatus(publishStatus,ids,PmsProduct::getPublishStatus);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }

    }

    /**
     * 商品添加工作量比较大，因为 商品主表 关联了 商品阶梯价格表、商品满减表、会员价格表、sku库存价格表、spu商品属性表
     *
     * todo 这个接口后面再写
     * @param
     * @return
     */
    @ApiOperation("商品添加")
    @RequestMapping(value="/create",method= RequestMethod.POST)
    public CommonResult create(){

        return CommonResult.success(true);

    }

    @ApiOperation("获取编辑状态下的信息")
    @RequestMapping(value="/updateInfo/{id}",method= RequestMethod.GET)
    public CommonResult updateInfo(@PathVariable Long id){

        return CommonResult.success(true);

    }

    @ApiOperation("商品修改-保存")
    @RequestMapping(value="/update/{id}",method= RequestMethod.POST)
    public CommonResult update(){

        return CommonResult.success(true);

    }

}


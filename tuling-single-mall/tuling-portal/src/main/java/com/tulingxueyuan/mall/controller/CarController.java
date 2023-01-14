package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.AddCarDto;
import com.tulingxueyuan.mall.dto.CartItemStockDto;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeMenusBannerDto;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeMenusDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页
 */
@Api(value="CarController",description = "购物车服务接口")
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private OmsCartItemService cartItemService;


    /**
     * 添加购物车
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CommonResult addCar(@RequestBody AddCarDto addCarDto){

        boolean result = cartItemService.addCar(addCarDto);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }

    }

    /**
     * 初始化状态栏购物车商品数量
     */
    @RequestMapping(value = "/sum",method = RequestMethod.GET)
    public CommonResult getCarProductSum(){

        Integer sum = cartItemService.getCarProductSum();
        return CommonResult.success(sum);

    }

    /**
     * 初始化购物车数据
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult getList(){

        List<CartItemStockDto> omsCartItemDtoList = cartItemService.getList();
        return CommonResult.success(omsCartItemDtoList);

    }

    /**
     * 更新商品数量
     * @param id
     * @param quantity
     * @return
     */
    @RequestMapping(value = "/update/quantity",method = RequestMethod.GET)
    public CommonResult updateQuantity(@RequestParam Long id,
                                       @RequestParam Integer quantity){

        boolean result = cartItemService.updateQuantity(id,quantity);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }

    }

    /**
     * 删除购车
     * @param ids
     * @param
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public CommonResult delete(@RequestParam List<Long> ids){

        boolean result = cartItemService.delete(ids);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }

    }





}

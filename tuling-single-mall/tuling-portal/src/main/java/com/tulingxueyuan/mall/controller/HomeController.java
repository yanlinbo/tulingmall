package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeMenusBannerDto;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeMenusDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页
 */
@Api(value="HomeController",description = "首页管理")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PmsProductCategoryService pmsProductCategoryService;
    @Autowired
    private SmsHomeAdvertiseService smsHomeAdvertiseService;

    /**
     * 获取首页导航栏和数据
     */
    @RequestMapping(value = "/menusAndBanner",method = RequestMethod.GET)
    public CommonResult getMenus(){

        /**
         * 这个接口的SQL没有写，后面再补
         */
        HomeMenusBannerDto homeMenusBannerDto = new HomeMenusBannerDto();
        //查询分类导航
        List<HomeMenusDto> menusList =  pmsProductCategoryService.getMenus();
        homeMenusBannerDto.setHomeMenusDtoList(menusList);
        //
        List<SmsHomeAdvertise> smsHomeAdvertiseList = smsHomeAdvertiseService.getHomeBanner();
        homeMenusBannerDto.setSmsHomeAdvertiseList(smsHomeAdvertiseList);
      return CommonResult.success(menusList);
    }

    /**
     * 由于没有sms_home_category所以这个接口暂时放着
     * 获取首页的推荐分类和商品列表
     */
    @RequestMapping(value = "/goodsSale",method = RequestMethod.GET)
    public CommonResult getGoodsSale(){

        return CommonResult.success(null);
    }



}

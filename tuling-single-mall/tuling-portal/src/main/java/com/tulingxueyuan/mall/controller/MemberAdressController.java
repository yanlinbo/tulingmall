package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.AddCarDto;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberReceiveAddress;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="memberAdressController",description = "收货地址接口接口")
@RestController
@RequestMapping("/member/adress")
public class MemberAdressController {

    @Autowired
    private UmsMemberReceiveAddressService receiveAddressService;

    /**
     * 添加收货地址
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CommonResult addCar(@RequestBody UmsMemberReceiveAddress memberReceiveAddress){

        boolean result = receiveAddressService.add(memberReceiveAddress);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }

    }

    /**
     * 更新收货地址
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public CommonResult edit(@RequestBody UmsMemberReceiveAddress memberReceiveAddress){

        boolean result = receiveAddressService.edit(memberReceiveAddress);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }

    }

    /**
     * 删除收货地址
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public CommonResult addCar(@RequestParam List<Long> ids){

        boolean result = receiveAddressService.delete(ids);
        if(result){
            return CommonResult.success(result);
        }else{
            return CommonResult.failed();
        }

    }

    /**
     * 根据用户查询收货地址
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult list(){

        List<UmsMemberReceiveAddress> addressList = receiveAddressService.listByMemberId();
        return CommonResult.success(addressList);

    }
}

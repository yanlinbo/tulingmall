package com.tulingxueyuan.mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.ConfirmOrderDto;
import com.tulingxueyuan.mall.dto.OrderDetailDto;
import com.tulingxueyuan.mall.dto.OrderListDto;
import com.tulingxueyuan.mall.dto.OrderParamDto;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderItemService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.log.LogInputStream;

import java.util.List;

/**
 * 首页
 */
@Api(value="orderController",description = "订单服务接口")
@RestController
@RequestMapping("/order")
public class orderController {

    @Autowired
    private OmsOrderItemService orderItemService;

    @Autowired
    OmsOrderService orderService;



    /**
     *
     *  加入购物车---生成确认订单实现
     *  立即购买—生成确认订单实现 product_id  sku_id. 改成DTO接收
     *    复用业务逻辑的代码 product_id 和sku_id 查出购物车对象所需要信息
     *  初始化确认订单的商品和收货地址信息
     *  todo:加入购物车已实现，立即购买还没有实现
     * this.axios.post('/order/generateConfirmOrder',Qs.stringify({itemIds: constStore.itemids}
     */
    @RequestMapping(value = "/confirm",method = RequestMethod.POST)
    public CommonResult generateConfirmOrder(@RequestParam("iterIds") List<Long> ids){

        ConfirmOrderDto confirmOrderDto = orderItemService.generateConfirmOrder(ids);
        return CommonResult.success(confirmOrderDto);

    }

    /**
     *  生成订单(下单）
     * this.axios
     *           .post("/order/generateOrder", {
     */
    @RequestMapping(value="/generateOrder",method = RequestMethod.POST)
    public CommonResult generateOrder(@RequestBody OrderParamDto paramDto){
        OmsOrder omsOrder = orderService.generateOrder(paramDto);
        return CommonResult.success(omsOrder.getId());
    }

    /**
     *  查询订单详情
     */
    @RequestMapping(value="/getOrderDetail",method = RequestMethod.GET)
    public CommonResult getOrderDetail(@PathVariable Long orderId){
        OrderDetailDto olrderDetailDto = orderService.getOrderDetail(orderId);
        return CommonResult.success(olrderDetailDto);
    }

    /**
     *  获取我的订单
     */
    @RequestMapping(value="/getMyOrderPage",method = RequestMethod.GET)
    public CommonResult getMyOrderPage(@RequestParam(value="pageNum",defaultValue ="1" ) Integer pageNum,
                                       @RequestParam(value="pageSize",defaultValue ="10" ) Integer pageSize){
        IPage<OrderListDto> orderListDtoPage = orderService.getMyOrder( pageNum, pageSize);
        return CommonResult.success(orderListDtoPage);
    }

    /**
     *  支付成功
     */
    @RequestMapping(value="/paySucess",method = RequestMethod.GET)
    public void paySucess(){
        System.out.println("支付成功");
        //更新订单状态和支付方式

        //清除锁定库存，扣除实际库存

        //删除二维码
    }





}

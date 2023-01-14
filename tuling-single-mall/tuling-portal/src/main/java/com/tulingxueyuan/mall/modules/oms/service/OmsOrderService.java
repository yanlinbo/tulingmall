package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tulingxueyuan.mall.dto.OrderDetailDto;
import com.tulingxueyuan.mall.dto.OrderListDto;
import com.tulingxueyuan.mall.dto.OrderParamDto;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2023-01-09
 */
public interface OmsOrderService extends IService<OmsOrder> {

    OmsOrder generateOrder(OrderParamDto paramDto);

    OrderDetailDto getOrderDetail(Long orderId);

    IPage<OrderListDto> getMyOrder(Integer pageNum, Integer pageSize);
}

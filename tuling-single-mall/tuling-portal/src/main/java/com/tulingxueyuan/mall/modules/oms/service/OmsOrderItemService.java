package com.tulingxueyuan.mall.modules.oms.service;

import com.tulingxueyuan.mall.dto.ConfirmOrderDto;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单中所包含的商品 服务类
 * </p>
 *
 * @author XuShu
 * @since 2023-01-09
 */
public interface OmsOrderItemService extends IService<OmsOrderItem> {

    ConfirmOrderDto generateConfirmOrder(List<Long> ids);
}

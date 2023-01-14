package com.tulingxueyuan.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.dto.OrderDetailDto;
import com.tulingxueyuan.mall.dto.OrderListDto;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2023-01-09
 */
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {

    OrderDetailDto getOrderDetail(Long id);

    IPage<OrderListDto> getMyOrder(Page<?> page, @Param("memberId") Long memberId);
}

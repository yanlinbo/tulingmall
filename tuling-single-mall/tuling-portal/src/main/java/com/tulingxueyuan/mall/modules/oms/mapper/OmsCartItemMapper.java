package com.tulingxueyuan.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.dto.CartItemStockDto;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2023-01-07
 */
public interface OmsCartItemMapper extends BaseMapper<OmsCartItem> {

    List<CartItemStockDto> getCartItermStock(Long memberId);

    List<CartItemStockDto> getCartItemStockByIds(QueryWrapper<OmsCartItem> queryWrapper);
}

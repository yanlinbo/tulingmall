package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.AddCarDto;
import com.tulingxueyuan.mall.dto.CartItemStockDto;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2023-01-07
 */
public interface OmsCartItemService extends IService<OmsCartItem> {

    boolean addCar(AddCarDto addCarDto);

    /**
     * 初始化状态栏购物车商品数量
     */
    Integer getCarProductSum();

    /**
     * 初始化购物车数据
     * @return
     */
    List<CartItemStockDto> getList();

    /**
     * 更新商品数量
     * @param id
     * @param quantity
     * @return
     */
    boolean updateQuantity(Long id, Integer quantity);

    /**
     * 删除商品
     * @param ids
     * @return
     */
    boolean delete(List<Long> ids);
}

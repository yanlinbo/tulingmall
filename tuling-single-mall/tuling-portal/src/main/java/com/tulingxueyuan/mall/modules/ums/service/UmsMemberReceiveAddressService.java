package com.tulingxueyuan.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberReceiveAddress;

import java.util.List;

/**
 * <p>
 * 会员收货地址表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2023-01-07
 */
public interface UmsMemberReceiveAddressService extends IService<UmsMemberReceiveAddress> {

    boolean add(UmsMemberReceiveAddress memberReceiveAddress);

    boolean delete(List<Long> ids);

    boolean edit(UmsMemberReceiveAddress memberReceiveAddress);

    List<UmsMemberReceiveAddress> listByMemberId();
}

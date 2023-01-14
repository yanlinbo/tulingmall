package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2023-01-02
 */
public interface SmsHomeAdvertiseMapper extends BaseMapper<SmsHomeAdvertise> {

    List<SmsHomeAdvertise> getHomeBanner();
}

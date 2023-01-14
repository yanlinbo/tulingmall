package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeAdvertiseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2023-01-02
 */
@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements SmsHomeAdvertiseService {

    @Autowired
    SmsHomeAdvertiseMapper smsHomeAdvertiseMapper;
    @Override
    public List<SmsHomeAdvertise> getHomeBanner() {
//        List<SmsHomeAdvertise> smsHomeAdvertiseList = smsHomeAdvertiseMapper.getHomeBanner();
        QueryWrapper<SmsHomeAdvertise> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SmsHomeAdvertise::getType,0)
                .eq(SmsHomeAdvertise::getStatus,1)
                .orderByAsc(SmsHomeAdvertise::getSort);
        List<SmsHomeAdvertise> list = this.list(queryWrapper);
        return list;
    }
}

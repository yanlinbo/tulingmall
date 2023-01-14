package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductConditionDto;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
public interface PmsProductService extends IService<PmsProduct> {

    Page<PmsProduct>  page(PmsProductConditionDto condition);

    boolean updateStatus(Integer recommendStatus, List<Long> ids, SFunction<PmsProduct,?> getRecommandStatus);
}

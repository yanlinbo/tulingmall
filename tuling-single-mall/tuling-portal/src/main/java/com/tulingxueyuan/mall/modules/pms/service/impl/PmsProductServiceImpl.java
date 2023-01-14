package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductConditionDto;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductDetailDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Override
    public Page<PmsProduct> page(PmsProductConditionDto condition) {
        Page page = new Page(condition.getPageNum(),condition.getPageSize());
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        //商品名称
        if(!StringUtils.isEmpty(condition.getKeyword())){
            queryWrapper.lambda().like(PmsProduct::getName,condition.getKeyword());
        }
        //货号
        if(!StringUtils.isEmpty(condition.getProductSn())){
            queryWrapper.lambda().like(PmsProduct::getProductSn,condition.getProductSn());
        }

        //品牌
        if(condition.getBrandId() != null){
            queryWrapper.lambda().like(PmsProduct::getProductSn,condition.getProductSn());
        }
        //上架状态
        if(condition.getVerifyStatus() != null){
            queryWrapper.lambda().like(PmsProduct::getVerifyStatus,condition.getVerifyStatus());
        }
        //品审核状态
        if(condition.getPublishStatus() != null){
            queryWrapper.lambda().like(PmsProduct::getPublishStatus,condition.getPublishStatus());
        }
        //商品分类
        if(condition.getProductCategoryId() != null){
            queryWrapper.lambda().like(PmsProduct::getProductCategoryId,condition.getProductCategoryId());
        }
        return this.page(page,queryWrapper);
    }

    @Override
    public boolean updateStatus(Integer status, List<Long> ids, SFunction<PmsProduct,?> getRecommandStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper();
        updateWrapper.lambda().set(getRecommandStatus,status).in(PmsProduct::getId,ids);
        return this.update(updateWrapper);
    }

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    @Override
    public ProductDetailDto getProductDetail(Long id) {

        return pmsProductMapper. getProductDetail(id);
    }
}

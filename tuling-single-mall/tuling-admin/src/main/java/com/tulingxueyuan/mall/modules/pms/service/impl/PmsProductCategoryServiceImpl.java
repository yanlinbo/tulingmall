package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDto;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCategoryDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {

    @Autowired
    PmsProductCategoryAttributeRelationService productCategoryAttributeRelationService;

    @Autowired
    PmsProductCategoryMapper pmsProductCategoryMapper;

    @Override
    public Page list(Long parentId, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum,pageSize);

        // 条件构造器 两种写法
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper();
        //写法一:缺点每个条件都要查看数据库表的字段是怎么写的；
        //queryWrapper.eq("parent_id",parentId);
        //写法二：
        queryWrapper.lambda().eq(PmsProductCategory::getParentId,parentId);
        return this.page(page,queryWrapper);
    }


    @Override
    @Transactional
    public boolean customSave(PmsProductCategoryDto productCategoryDto) {
        //1，保存商品分类
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        //对象复制
        BeanUtils.copyProperties(productCategoryDto,pmsProductCategory);
        pmsProductCategory.setProductCount(0);
        if(productCategoryDto.getParentId() == 0){
            pmsProductCategory.setLevel(0);
        }else{
            //如果有多级分类，根据parentID查出商品分类获取level+1
            pmsProductCategory.setLevel(1);
        }
        this.save(pmsProductCategory);

        saveAttrRelation(productCategoryDto, pmsProductCategory);
        return true;
    }

    @Override
    @Transactional
    public boolean update(PmsProductCategoryDto productCategoryDto) {
        //1，保存商品分类
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        //对象复制
        BeanUtils.copyProperties(productCategoryDto,pmsProductCategory);
        pmsProductCategory.setProductCount(0);
        if(productCategoryDto.getParentId() == 0){
            pmsProductCategory.setLevel(0);
        }else{
            //如果有多级分类，根据parentID查出商品分类获取level+1
            pmsProductCategory.setLevel(1);
        }
        this.save(pmsProductCategory);

        //删除已保存的关联属性
        QueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(PmsProductCategoryAttributeRelation :: getProductCategoryId,pmsProductCategory.getId());
        productCategoryAttributeRelationService.remove(queryWrapper);
        saveAttrRelation(productCategoryDto, pmsProductCategory);
        return true;
    }

    @Override
    public List<ProductAttributeCategoryDto> getWithChildren() {

        return pmsProductCategoryMapper.getWithChildren();
    }

    /**
     * 添加关联属性表
     * @param productCategoryDto
     * @param pmsProductCategory
     * @return
     */
    private boolean saveAttrRelation(PmsProductCategoryDto productCategoryDto, PmsProductCategory pmsProductCategory) {
        boolean isSaveII;
        List<Long> pmsProductAttributeIdList = productCategoryDto.getPmsProductAttributeIdList();
        List<PmsProductCategoryAttributeRelation> productCategoryAttributeRelationList = new ArrayList<>();
        for (Long attrId : pmsProductAttributeIdList) {
            //维护关联表
            PmsProductCategoryAttributeRelation productCategoryAttributeRelation = new PmsProductCategoryAttributeRelation();
            productCategoryAttributeRelation.setProductCategoryId(pmsProductCategory.getId());
            productCategoryAttributeRelation.setProductAttributeId(attrId);
            productCategoryAttributeRelationList.add(productCategoryAttributeRelation);
        }
        isSaveII = productCategoryAttributeRelationService.saveBatch(productCategoryAttributeRelationList);
        return isSaveII;
    }
}

package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.RelationAttrInfoDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements PmsProductAttributeService {

    @Autowired
    public PmsProductAttributeCategoryService pmsProductAttributeCategoryService;
    @Autowired
    public PmsProductAttributeMapper pmsProductAttributeMapper;

    @Override
    public Page list(Long cid, String type, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum,pageSize);
        QueryWrapper<PmsProductAttribute> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(PmsProductAttribute::getId,cid)
                             .eq(PmsProductAttribute::getType,type);
        return this.page(page,queryWrapper);
    }

    @Override
    public Boolean create(PmsProductAttribute pmsProductAttribute) {
        //1,保存商品属性
        boolean save = this.save(pmsProductAttribute);
        if(save){
            //2,更新对应的属性、参数数量  两种方式：（1）先查询在更新（多一次查询影响性能） （2）直接更新
            UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper();
            if(pmsProductAttribute.getType() == 0){
                updateWrapper.setSql("attribute_count = attribute_count + 1 ");
            }else if(pmsProductAttribute.getType() == 1){
                updateWrapper.setSql("param_count = param_count + 1 ");
            }
            updateWrapper.lambda().eq(PmsProductAttributeCategory :: getId,pmsProductAttribute.getProductAttributeCategoryId());
            pmsProductAttributeCategoryService.update(updateWrapper);
        }

        return save;
    }

    @Override
    public int delete(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){
            return 0;
        }
        //这个校验锦上添花的作用
        PmsProductAttribute pmsProductAttribute = null;
        for (Long id : ids) {
            pmsProductAttribute = this.getById(id);
            if(pmsProductAttribute != null){
                break;
            }
        }
        int length = pmsProductAttributeMapper.deleteBatchIds(ids);
        if(length > 0 && pmsProductAttribute != null){
            //2,更新对应的属性、参数数量  两种方式：（1）先查询在更新（多一次查询影响性能） （2）直接更新
            UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper();
            if(pmsProductAttribute.getType() == 0){
                updateWrapper.setSql("attribute_count = attribute_count-"+length);
            }else if(pmsProductAttribute.getType() == 1){
                updateWrapper.setSql("param_count = param_count-"+length);
            }
            updateWrapper.lambda().eq(PmsProductAttributeCategory :: getId,pmsProductAttribute.getProductAttributeCategoryId());
            pmsProductAttributeCategoryService.update(updateWrapper);
        }

        return length;
    }

    @Override
    public List<RelationAttrInfoDto> getRelationAttrInfoByCid(Long cid) {
        List<RelationAttrInfoDto> relationAttrInfoDtoList =  pmsProductAttributeMapper.getRelationAttrInfoByCid(cid);
        return null;
    }
}

package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeMenusDto;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDto;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCategoryDto;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

//    public Page list(Long parentId, Integer pageNum, Integer pageSize);
//
//    boolean customSave(PmsProductCategoryDto productCategoryDto);
//
//    boolean update(PmsProductCategoryDto productCategoryDto);
//
//    /**
//     * 获取商品的一级分类和二级分类的下拉级联数据
//     * @return
//     */
//    List<ProductAttributeCategoryDto> getWithChildren();

    /**
     * 获取首页类型导航菜单
     * @return
     */
    List<HomeMenusDto> getMenus();
}

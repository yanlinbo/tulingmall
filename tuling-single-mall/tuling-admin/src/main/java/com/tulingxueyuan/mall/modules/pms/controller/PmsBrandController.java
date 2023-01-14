package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.service.PmsBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-12-31
 */
@RestController
@RequestMapping("/pms/pmsBrand")
public class PmsBrandController {

    @Autowired
    PmsBrandService pmsBrandService;

    @ApiOperation("查询品牌数据列表")
    @RequestMapping(value="/list/{parentId}",method= RequestMethod.GET)
    public CommonResult<CommonPage<PmsBrand>> list(
            @RequestParam(value="keyword",defaultValue = "") String keyword,
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize
    ){

        Page<PmsBrand> page = pmsBrandService.page( keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

}


package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.common.api.ResultCode;
import com.tulingxueyuan.mall.common.exception.Asserts;
import com.tulingxueyuan.mall.dto.AddCarDto;
import com.tulingxueyuan.mall.dto.CartItemStockDto;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsCartItemMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import com.tulingxueyuan.mall.modules.pms.service.PmsSkuStockService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2023-01-07
 */
@Service
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemMapper, OmsCartItem> implements OmsCartItemService {
    
    @Autowired
    UmsMemberService memberService;

    @Autowired
    PmsSkuStockService skuStockService;

    @Autowired
    PmsProductService productService;

    @Autowired
    OmsCartItemMapper cartItemMapper;
    
    @Override
    public boolean addCar(AddCarDto addCarDto) {
        OmsCartItem cartItem = new OmsCartItem();
        BeanUtils.copyProperties(addCarDto,cartItem);
        //获取当前登录人
        UmsMember currentMember = memberService.getCurrentMember();
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());

        //判断同一个商品、sku、用户下是否添加重复的购物车
        OmsCartItem cartItemTemp = getOmsCartItem(cartItem.getProductId(), cartItem.getProductSkuId(), cartItem.getMemberId());
        //如果商品不存在，新增商品
        if(cartItemTemp == null){
            //todo 如果是下单的话，这里需要加同步锁，原因是我们在查询的时候有库存，但是在实际买的时候，并发导致没库存了
            //获取sku
            PmsSkuStock skuStock = skuStockService.getById(addCarDto.getProductSkuId());
            //这里防止前端恶意篡改，需要做一个校验
            if(skuStock == null){
                Asserts.fail(ResultCode.VALIDATE_FAILED);
            }
            cartItem.setPrice(skuStock.getPrice());
            cartItem.setSp1(skuStock.getSp1());
            cartItem.setSp2(skuStock.getSp2());
            cartItem.setSp3(skuStock.getSp3());
            cartItem.setProductPic(skuStock.getPic());
            cartItem.setProductSkuCode(skuStock.getSkuCode());
            //查询商品
            PmsProduct product = productService.getById(cartItem.getProductId());
            if(product == null){
                Asserts.fail(ResultCode.VALIDATE_FAILED);
            }
            cartItem.setProductName(product.getName());
            cartItem.setProductBrand(product.getBrandName());
            cartItem.setProductSn(product.getProductSn());
            cartItem.setProductSubTitle(product.getSubTitle());
            cartItem.setProductCategoryId(product.getProductCategoryId());
            cartItem.setCreateDate(new Date());
            cartItem.setModifyDate(new Date());
            return baseMapper.insert(cartItem)>0;
        }else{
            //如果商品存在，商品数量加一
            cartItemTemp.setQuantity(cartItem.getQuantity()+1);
            cartItemTemp.setModifyDate(new Date());
            UpdateWrapper<OmsCartItem> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda().set(OmsCartItem::getQuantity,cartItemTemp.getQuantity())
                    .set(OmsCartItem::getModifyDate,cartItemTemp.getModifyDate())
                    .set(OmsCartItem::getId,cartItemTemp.getId());
            return baseMapper.update(cartItemTemp,updateWrapper) > 0;
        }

    }

    @Override
    public Integer getCarProductSum() {

        //获取当前登录人
        UmsMember currentMember = memberService.getCurrentMember();
        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("sum(quantity) as tatal").lambda()
                .eq(OmsCartItem::getMemberId,currentMember.getId());
        List<Map<String, Object>> cartItemList = baseMapper.selectMaps(queryWrapper);
        if(cartItemList != null && cartItemList.size()==1){
            Map<String, Object> map = cartItemList.get(0);
            if(map.get("tatal") != null){
                return Integer.parseInt(map.get("tatal").toString());
            }
        }

        return 0;
    }

    @Override
    public List<CartItemStockDto> getList() {
        //获取当前用户
        UmsMember member = memberService.getCurrentMember();
        QueryWrapper<OmsCartItem> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(OmsCartItem::getMemberId,member.getId());

        List<CartItemStockDto> cartItemStock = cartItemMapper.getCartItermStock(member.getId());
//        return this.list(wrapper);
        return cartItemStock;
    }

    /**
     * 更新商品数量
     * @param id
     * @param quantity
     * @return
     */
    @Override
    public boolean updateQuantity(Long id, Integer quantity) {
        UpdateWrapper<OmsCartItem> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(OmsCartItem::getQuantity,quantity)
                .eq(OmsCartItem::getId,id);

        return this.update(updateWrapper);
    }

    @Override
    public boolean delete(List<Long> ids) {
        return this.removeByIds(ids);
    }

    /**
     * 判断同一个商品、sku、用户下是否添加重复的购物车
     * @param productId
     * @param skuId
     * @param memberId
     * @return
     */
    public OmsCartItem getOmsCartItem(Long productId,Long skuId,Long memberId){

        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(OmsCartItem::getProductId,productId)
                .eq(OmsCartItem::getId,skuId)
                .eq(OmsCartItem::getMemberId,memberId);
        return baseMapper.selectOne(queryWrapper);
    }
}

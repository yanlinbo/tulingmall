package com.tulingxueyuan.mall.modules.oms.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tulingxueyuan.mall.common.api.ResultCode;
import com.tulingxueyuan.mall.common.exception.ApiException;
import com.tulingxueyuan.mall.dto.ConfirmOrderDto;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderItemMapper;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberReceiveAddress;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberReceiveAddressService;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2023-01-09
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements OmsOrderItemService {

    @Autowired
    private OmsCartItemService cartItemService;

    @Autowired
    PmsProductService productService;

    @Autowired
    UmsMemberReceiveAddressService addressService;

    @Autowired
    UmsMemberService memberService;

    @Override
    public ConfirmOrderDto generateConfirmOrder(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
        ConfirmOrderDto confirmOrderDto = new ConfirmOrderDto();
        //1,商品
        List<OmsCartItem> itemList = cartItemService.listByIds(ids);
        confirmOrderDto.setCartItemList(itemList);
        //2，计算价格
        calcCatAmount(confirmOrderDto);
        //3,收货地址
        UmsMember member = memberService.getCurrentMember();
        QueryWrapper<UmsMemberReceiveAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsMemberReceiveAddress::getMemberId,member.getId());
        List<UmsMemberReceiveAddress> addressList = addressService.list(queryWrapper);
        confirmOrderDto.setAdressList(addressList);
        return confirmOrderDto;
    }

    public void calcCatAmount(ConfirmOrderDto confirmOrderDto){


        //商品总数量
        Integer productTotal=0;
        //总价
        BigDecimal priceTotal = new BigDecimal(0);
        //运费
        BigDecimal freightAmount = new BigDecimal(0);

        for (OmsCartItem omsCartItem : confirmOrderDto.getCartItemList()) {
            productTotal+=omsCartItem.getQuantity();
            priceTotal.add(omsCartItem.getPrice().multiply(new BigDecimal(omsCartItem.getQuantity())));
            PmsProduct product = productService.getById(omsCartItem.getProductId());
            String[] serviceIdsArray = product.getServiceIds().split(",");
            if(serviceIdsArray.length > 0){
                //判断是否包邮
                if(ArrayUtil.contains(serviceIdsArray,"3")){
                    freightAmount.add(new BigDecimal(10));
                }
            }
        }
        confirmOrderDto.setPriceTotal(priceTotal);
        confirmOrderDto.setFreightAmount(freightAmount);
        confirmOrderDto.setPayAmount(priceTotal.subtract(freightAmount));
        confirmOrderDto.setProductTotal(productTotal);
    }
}

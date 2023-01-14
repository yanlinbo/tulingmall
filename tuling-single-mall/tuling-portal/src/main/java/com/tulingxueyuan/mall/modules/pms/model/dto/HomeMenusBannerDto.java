package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="商品导航栏和横幅",description = "商品导航栏和横幅")
public class HomeMenusBannerDto {

    private List<HomeMenusDto> homeMenusDtoList;

    private List<SmsHomeAdvertise> smsHomeAdvertiseList;
}

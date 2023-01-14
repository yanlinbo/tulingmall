package com.tulingxueyuan.mall.modules.sms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 限时购通知记录
 * </p>
 *
 * @author XuShu
 * @since 2023-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_flash_promotion_log")
@ApiModel(value="SmsFlashPromotionLog对象", description="限时购通知记录")
public class SmsFlashPromotionLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer memberId;

    private Long productId;

    private String memberPhone;

    private String productName;

    @ApiModelProperty(value = "会员订阅时间")
    private Date subscribeTime;

    private Date sendTime;


}

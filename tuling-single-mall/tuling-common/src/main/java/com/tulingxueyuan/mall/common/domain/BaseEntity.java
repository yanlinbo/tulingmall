package com.tulingxueyuan.mall.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //如果实现了Serializable，必须有这个序列化版本UID

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "修改时间")
    private Date modifyDate;

    @ApiModelProperty(value = "创建人")
    private Date creator;

    @ApiModelProperty(value = "修改人")
    private Date modifior;

    @ApiModelProperty(value = "是否删除")
    private Integer deleteStatus;
}

package com.tulingxueyuan.mall.modules.bank.service.impl;

import com.tulingxueyuan.mall.modules.bank.service.BglProcessInteface;
import org.springframework.beans.factory.InitializingBean;

public class Process22131 implements BglProcessInteface {
    @Override
    public void bglValidate() {
        System.out.println("----Process22131 校验-----");
    }

    @Override
    public void bglFinancial() {
        System.out.println("----Process22131 调金融交易-----");
    }

    @Override
    public void bglOldWire() {
        System.out.println("----Process22131 调旧线主机-----");
    }

    @Override
    public String build(Object context) {
        System.out.println("构建22031交易的消息体");
        return "构建22031交易的消息体";
    }

    @Override
    public String buildName() {
        return "22131交易";
    }

}

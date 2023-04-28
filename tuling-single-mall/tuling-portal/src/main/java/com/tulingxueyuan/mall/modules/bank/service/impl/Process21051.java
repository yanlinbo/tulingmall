package com.tulingxueyuan.mall.modules.bank.service.impl;

/**
 * 21开头的交易继承抽象类，目的是是模板设计模式来实现
 */
public class Process21051 extends BglProcess {
    @Override
    public void bglValidate() {
        System.out.println("----Process22031 校验-----");
    }

    @Override
    public void bglFinancial() {
        System.out.println("----Process22031 调金融交易-----");
    }

    @Override
    public void bglOldWire() {
        System.out.println("----Process22031 调旧线主机-----");
    }
}

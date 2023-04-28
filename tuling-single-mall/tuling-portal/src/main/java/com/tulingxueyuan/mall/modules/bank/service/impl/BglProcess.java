package com.tulingxueyuan.mall.modules.bank.service.impl;

public abstract class BglProcess {


    public abstract void bglValidate();

    public abstract void bglFinancial();

    public abstract void bglOldWire();


    public void bglProcess(){
        //第一步：
        this.bglValidate();
        //第二步：
        this.bglFinancial();
        //第三步：
        this.bglOldWire();
    }
}

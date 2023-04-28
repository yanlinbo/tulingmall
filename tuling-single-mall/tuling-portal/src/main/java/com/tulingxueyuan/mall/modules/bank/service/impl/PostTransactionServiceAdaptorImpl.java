package com.tulingxueyuan.mall.modules.bank.service.impl;

import com.google.common.collect.Maps;
import com.tulingxueyuan.mall.modules.bank.service.BglProcessInteface;
import com.tulingxueyuan.mall.modules.bank.service.PostTransactionServiceAdaptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class PostTransactionServiceAdaptorImpl<T> implements PostTransactionServiceAdaptor, InitializingBean {
    @Autowired
    private ApplicationContext applicationContext;

    private Map<String,BglProcessInteface> bglProcessIntefaceMap = Maps.newHashMap();

    @Override
    public void process(Object context) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationContext.getBeansOfType(BglProcessInteface.class)
                .values()
                .stream()
                .forEachOrdered(bglProcessInteface -> bglProcessIntefaceMap.put(bglProcessInteface.buildName(),bglProcessInteface));
    }
}

package com.ylb.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 产生bean对象的工程
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * bean工厂生成bean对象
     * @param name
     * @return
     */
    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

    /**
     * 注册beanDefinition
     * @param name
     * @param beanDefinition
     */
    public void registerBeanDefination(String name, BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }
}

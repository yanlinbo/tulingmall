package com.ylb.springframework.beans.factory.support;

import com.ylb.springframework.beans.BeansException;
import com.ylb.springframework.beans.factory.BeanFactory;
import com.ylb.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    /**
     * 该方法是一个模板方法
     * @param name
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String name) throws BeansException {

        return doGetBean(name, null);
    }

    public Object getBean(String name, Object... args) throws BeansException{

        return doGetBean(name, args);
    }

    public <T> T doGetBean(final String name, final Object[] args) throws BeansException {
        //从单例池中获取单例
        Object singleton = getSingleton(name);
        if(singleton != null){
            return (T)singleton;
        }
        //如果单例池中没有单例，通过下面模板步骤获取
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name,beanDefinition,args);
    }

    protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;
}

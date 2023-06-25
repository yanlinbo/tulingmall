package com.ylb.springframework.beans.factory.support;

import com.ylb.springframework.beans.BeansException;
import com.ylb.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object... args) throws BeansException {
        Object bean = null;
        try {
//            bean = beanDefinition.getBeanClass().newInstance();  //这种实例化方式并没有考虑构造函数的入参
            bean = createBeanInstance(beanName,beanDefinition,args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors){
            if(args != null && ctor.getTypeParameters().length+1 == args.length){
                constructorToUse = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition,beanName,constructorToUse,args);
    }

}

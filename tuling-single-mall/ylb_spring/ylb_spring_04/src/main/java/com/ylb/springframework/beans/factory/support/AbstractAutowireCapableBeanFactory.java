package com.ylb.springframework.beans.factory.support;


import cn.hutool.core.bean.BeanUtil;
import com.ylb.springframework.beans.BeansException;
import com.ylb.springframework.beans.PropertyValue;
import com.ylb.springframework.beans.PropertyValues;
import com.ylb.springframework.beans.factory.config.BeanDefinition;
import com.ylb.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object... args) throws BeansException {
        Object bean = null;
        try {
//            bean = beanDefinition.getBeanClass().newInstance();  //这种实例化方式并没有考虑构造函数的入参
            bean = createBeanInstance(beanName,beanDefinition,args);

            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for(PropertyValue propertyValue : propertyValues.getPropertyValues()){
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                // A 依赖 B，获取 B 的实例化
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            //属性填充  todo 这个方法抽时间手动实现一下
            BeanUtil.setFieldValue(bean, name, value);
        }
    };

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

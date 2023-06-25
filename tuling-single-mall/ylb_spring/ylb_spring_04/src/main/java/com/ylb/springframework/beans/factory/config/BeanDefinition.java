package com.ylb.springframework.beans.factory.config;

import com.ylb.springframework.beans.PropertyValues;

public class BeanDefinition {
    private Class beanClass;

    private PropertyValues propertyValues;

    /**
     * 把两个构造函数做了一些简单的优化，避免后面 for 循环时还得判断属性填充是否为空
     * @param beanClass
     * @param propertyValues
     */
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }
}

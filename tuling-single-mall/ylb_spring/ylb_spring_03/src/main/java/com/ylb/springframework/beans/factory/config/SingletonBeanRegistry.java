package com.ylb.springframework.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}

package com.ylb.springframework.beans.factory;

import com.ylb.springframework.beans.BeansException;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;
}

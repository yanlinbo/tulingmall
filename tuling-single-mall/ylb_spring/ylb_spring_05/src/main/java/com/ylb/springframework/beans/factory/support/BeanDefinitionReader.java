package com.ylb.springframework.beans.factory.support;

import com.ylb.springframework.beans.BeansException;
import com.ylb.springframework.core.io.Resource;
import com.ylb.springframework.core.io.ResourceLoader;

import java.io.IOException;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException, IOException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}

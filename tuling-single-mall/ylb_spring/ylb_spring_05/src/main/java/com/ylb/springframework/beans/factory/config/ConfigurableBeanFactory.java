package com.ylb.springframework.beans.factory.config;

import com.ylb.springframework.beans.factory.BeanFactory;
import com.ylb.springframework.beans.factory.HierarchicalBeanFactory;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

}

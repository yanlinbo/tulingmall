package com.ylb.springframework.test;

import com.ylb.springframework.beans.BeansException;
import com.ylb.springframework.beans.PropertyValue;
import com.ylb.springframework.beans.PropertyValues;
import com.ylb.springframework.beans.factory.config.BeanDefinition;
import com.ylb.springframework.beans.factory.config.BeanReference;
import com.ylb.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ylb.springframework.test.bean.UserService;
import com.ylb.springframework.test.dao.UserDao;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

public class ApiTest {

    @Test
    public void test_BeanFactory() throws BeansException {

        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 UserDao
        BeanDefinition beanDefinitionUserDao = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao",beanDefinitionUserDao);
        // 3.往UserService中设置属性 uId UserDao
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        // 4. UserService 注入bean
        BeanDefinition beanDefinitionUserService = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinitionUserService);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}

package com.ylb.springframework.test;

import com.ylb.springframework.BeanDefinition;
import com.ylb.springframework.BeanFactory;
import com.ylb.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory(){

        // 1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();
        // 2.注册 bean  todo 在 Bean 的注册中，这里是直接把 UserService 实例化后作为入参传递给 BeanDefinition 的，在后续的陆续实现中，我们会把这部分内容放入 Bean 工厂中实现。
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefination("userService",beanDefinition);
        // 3.获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}

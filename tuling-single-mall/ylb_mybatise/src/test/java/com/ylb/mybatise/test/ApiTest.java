package com.ylb.mybatise.test;

import com.ylb.mybatise.binding.MapperProxyFactory;
import com.ylb.mybatise.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class ApiTest {

    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void testMapperProxyFactory(){
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("com.ylb.mybatise.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("com.ylb.mybatise.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
        IUserDao userDao = factory.newInstance(sqlSession);
        String res = userDao.queryUserName("10001");
        log.info("测试结果：{}", res);
    }
}

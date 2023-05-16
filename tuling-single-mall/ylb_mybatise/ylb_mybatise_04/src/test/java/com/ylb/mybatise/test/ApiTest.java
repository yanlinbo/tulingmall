package com.ylb.mybatise.test;

import com.alibaba.fastjson.JSON;
import com.ylb.mybatise.binding.MapperProxyFactory;
import com.ylb.mybatise.binding.MapperRegistry;
import com.ylb.mybatise.build.xml.XMLConfigBuilder;
import com.ylb.mybatise.io.Resources;
import com.ylb.mybatise.session.Configuration;
import com.ylb.mybatise.session.SqlSession;
import com.ylb.mybatise.session.SqlSessionFactory;
import com.ylb.mybatise.session.SqlSessionFactoryBuilder;
import com.ylb.mybatise.session.defaults.DefaultSqlSession;
import com.ylb.mybatise.session.defaults.DefaultSqlSessionFactory;
import com.ylb.mybatise.test.dao.IUserDao;
import com.ylb.mybatise.test.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;


public class ApiTest {

    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void testMapperProxyFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.opeSqlSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_selectOne() throws IOException {
        // 解析 XML
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        // 获取 DefaultSqlSession
        SqlSession sqlSession = new DefaultSqlSession(configuration);

        // 执行查询：默认是一个集合参数
        Object[] req = {1L};
        Object res = sqlSession.selectOne("com.ylb.mybatise.test.dao.IUserDao.queryUserInfoById", req);
        log.info("测试结果：{}", JSON.toJSONString(res));
    }


}

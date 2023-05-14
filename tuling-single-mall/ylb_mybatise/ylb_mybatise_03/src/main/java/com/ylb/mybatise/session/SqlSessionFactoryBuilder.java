package com.ylb.mybatise.session;

import com.ylb.mybatise.build.xml.XMLConfigBuilder;
import com.ylb.mybatise.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * 整个 Mybatis 的入口类，通过指定解析XML的IO，引导整个流程的启动
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    private SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}

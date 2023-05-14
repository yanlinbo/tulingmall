package com.ylb.mybatise.session.defaults;

import com.ylb.mybatise.binding.MapperRegistry;
import com.ylb.mybatise.mapping.MappedStatement;
import com.ylb.mybatise.session.Configuration;
import com.ylb.mybatise.session.SqlSession;

public class DefaultSqlSession implements SqlSession {
    /**
     * 映射器注册机
     */
//    private final MapperRegistry mapperRegistry;
//
//    public DefaultSqlSession(MapperRegistry mapperRegistry) {
//        this.mapperRegistry = mapperRegistry;
//    }

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

//    @Override
//    public <T> T selectOne(String statement) {
//        return (T) ("你被代理了！" + "方法：" + statement);
//    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" + "\n方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}

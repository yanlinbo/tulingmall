package com.ylb.mybatise.session;

import com.ylb.mybatise.binding.MapperRegistry;
import com.ylb.mybatise.datasource.druid.DruidDataSourceFactory;
import com.ylb.mybatise.mapping.Environment;
import com.ylb.mybatise.mapping.MappedStatement;
import com.ylb.mybatise.transaction.jdbc.JdbcTransactionFactory;
import com.ylb.mybatise.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    //环境
    protected Environment environment;

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }
    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

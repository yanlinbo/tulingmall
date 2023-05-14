package com.ylb.mybatise.session.defaults;

import com.ylb.mybatise.binding.MapperRegistry;
import com.ylb.mybatise.session.SqlSession;
import com.ylb.mybatise.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession opeSqlSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}

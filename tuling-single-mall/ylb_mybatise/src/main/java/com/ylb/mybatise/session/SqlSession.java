package com.ylb.mybatise.session;

/**
 * SqlSession、DefaultSqlSession 用于定义执行 SQL 标准、获取映射器以及将来管理事务等方面的操作。
 * 基本我们平常使用 Mybatis 的 API 接口也都是从这个接口类定义的方法进行使用的。
 */
public interface SqlSession {

//    <T> T selectOne(String statement);

    <T> T selectOne(String statement,Object parameter);

    <T> T getMapper(Class<T> type);
}

package com.ylb.mybatise.session;

/**
 * SqlSessionFactory 是一个简单工厂模式，用于提供 SqlSession 服务，屏蔽创建细节，延迟创建过程
 */
public interface SqlSessionFactory {

    SqlSession opeSqlSession();

}

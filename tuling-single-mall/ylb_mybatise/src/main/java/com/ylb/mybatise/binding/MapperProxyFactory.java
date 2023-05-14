package com.ylb.mybatise.binding;

import com.ylb.mybatise.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * MapperProxyFactory
 * 1,是对 MapperProxy 的包装，对外提供实例化对象的操作
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    /**
     * 工厂操作相当于把代理的创建给封装起来了，如果不做这层封装，那么每一个创建代理类的操作，都需要自己使用 Proxy.newProxyInstance 进行处理，那么这样的操作方式就显得比较麻烦了
     * @param sqlSession
     * @return
     */
//    public T newInstance(Map<String,String> sqlSession){
//        final MapperProxy MapperProxy = new MapperProxy(sqlSession,mapperInterface);
//        return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, MapperProxy);
//    }

    public T newInstance(SqlSession sqlSession){
        final MapperProxy MapperProxy = new MapperProxy<>(sqlSession,mapperInterface);
        return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, MapperProxy);
    }
}

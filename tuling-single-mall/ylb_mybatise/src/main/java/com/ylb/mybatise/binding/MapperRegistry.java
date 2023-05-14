package com.ylb.mybatise.binding;

import cn.hutool.core.lang.ClassScanner;
import com.ylb.mybatise.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * MapperRegistry 提供包路径的扫描和映射器代理类注册机服务，完成接口对象的代理类注册处理
 */
public class MapperRegistry {

    //将已添加的映射器代理工厂加入到 HashMap
    private final Map<Class<?>,MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>)knownMappers.get(type);
        if(mapperProxyFactory == null){
            throw new RuntimeException("type"+type+"is not known to the MapperRegistry");
        }
        return mapperProxyFactory.newInstance(sqlSession);
    }

    public <T> void addMapper(Class<T> type){

        /* Mapper 必须是接口才会注册 */
        if(type.isInterface()){
            if(hasMapper(type)){
                // 如果重复添加了，报错
                throw new RuntimeException("type" + type + " is already known to MapperRegistry");
            }
            knownMappers.put(type, new MapperProxyFactory<>(type));

        }
    }

    private <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public void addMappers(String packageName){
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }

    }





}

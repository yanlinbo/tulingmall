package com.ylb.mybatise.binding;

import com.ylb.mybatise.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 映射器代理类
 * 1,MapperProxy 负责实现 InvocationHandler 接口的 invoke 方法，最终所有的实际调用都会调用到这个方法包装的逻辑。
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

//    private Map<String,String> sqlSession;
    private SqlSession sqlSession;
    private final Class<T> mapperInterface;  //为什么加了final后必须生成构造方法
    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果是 Object 提供的 toString、hashCode 等方法是不需要代理执行的，所以添加 Object.class.equals(method.getDeclaringClass())
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        }else{
            final MapperMethod mapperMethod = cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }

    }

    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            //找不到才去new
            mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}

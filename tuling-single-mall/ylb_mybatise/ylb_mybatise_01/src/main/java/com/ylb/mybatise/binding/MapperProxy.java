package com.ylb.mybatise.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 映射器代理类
 * 1,MapperProxy 负责实现 InvocationHandler 接口的 invoke 方法，最终所有的实际调用都会调用到这个方法包装的逻辑。
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private Map<String,String> sqlSession;
    private final Class<T> mapperInteface;  //为什么加了final后必须生成构造方法

    public MapperProxy(Map<String,String> sqlSession, Class<T> mapperInteface) {
        this.sqlSession = sqlSession;
        this.mapperInteface = mapperInteface;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果是 Object 提供的 toString、hashCode 等方法是不需要代理执行的，所以添加 Object.class.equals(method.getDeclaringClass())
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        }else{
            return "你被代理了！" + sqlSession.get(mapperInteface.getName()+"."+method.getName());
        }

    }
}

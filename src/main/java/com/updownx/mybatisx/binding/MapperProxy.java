package com.updownx.mybatisx.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = 1L;
    private final Class<T> mapperInterface;
    //所有的数据库语句操作，都是通过接口名称+方法名称作为key，操作作为逻辑的方式进行使用的。
    private final Map<String,String> sqlSession;

    public MapperProxy(Map<String,String> sqlSession,Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果是 Object 提供的 toString、hashCode 等方法是不需要代理执行的
        if(Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }else {
            return "你被代理了！" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }

}

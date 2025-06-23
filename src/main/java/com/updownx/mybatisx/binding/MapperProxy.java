package com.updownx.mybatisx.binding;

import com.updownx.mybatisx.session.SqlSession;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 映射器代理类
 *
 * @param <T>
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
  private static final long serialVersionUID = 1L;
  private final Class<T> mapperInterface;
  // 所有的数据库语句操作，都是通过接口名称+方法名称作为key，操作作为逻辑的方式进行使用的。
  private final SqlSession sqlSession;
  private final Map<Method, MapperMethod> methodCache;

  public MapperProxy(
      SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
    this.methodCache = methodCache;
    this.sqlSession = sqlSession;
    this.mapperInterface = mapperInterface;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // 如果是 Object 提供的 toString、hashCode 等方法是不需要代理执行的
    if (Object.class.equals(method.getDeclaringClass())) {
      return method.invoke(this, args);
    } else {
      final MapperMethod mapperMethod = cacheMapperMethod(method);
      return mapperMethod.execute(sqlSession, args);
    }
  }

  /** 去缓存中找 MapperMethod */
  private MapperMethod cacheMapperMethod(Method method) {
    MapperMethod mapperMethod = methodCache.get(method);
    if (mapperMethod == null) {
      // 找不到才去 new
      mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
      methodCache.put(method, mapperMethod);
    }
    return mapperMethod;
  }
}

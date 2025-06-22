package com.updownx.mybatisx.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 工厂操作相当于把代理的创建给封装起来了，如果不做这层封装，那么每一个创建代理类的操作，都需要自己使用 Proxy.newProxyInstance 进行处理
 *
 * @param <T>
 */
public class MapperProxyFactory<T> {
  private final Class<T> mapperInterface;

  public MapperProxyFactory(Class<T> mapperInterface) {
    this.mapperInterface = mapperInterface;
  }

  public T newInstance(Map<String, String> sqlSession) {
    final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
    return (T)
        Proxy.newProxyInstance(
            mapperInterface.getClassLoader(), new Class[] {mapperInterface}, mapperProxy);
  }
}

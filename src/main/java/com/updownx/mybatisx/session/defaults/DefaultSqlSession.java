package com.updownx.mybatisx.session.defaults;

import com.updownx.mybatisx.binding.MapperRegistry;
import com.updownx.mybatisx.session.SqlSession;

public class DefaultSqlSession implements SqlSession {
  // 映射器注册机
  private MapperRegistry mapperRegistry;

  public DefaultSqlSession(MapperRegistry mapperRegistry) {
    this.mapperRegistry = mapperRegistry;
  }

  @Override
  public <T> T getMapper(Class<T> type) {
    return mapperRegistry.getMapper(type, this);
  }

  @Override
  public <T> T selectOne(String statement) {
    return this.selectOne(statement, null);
  }

  @Override
  public <T> T selectOne(String statement, Object parameter) {
    return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
  }
}

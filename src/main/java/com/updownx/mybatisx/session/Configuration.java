package com.updownx.mybatisx.session;

import com.updownx.mybatisx.binding.MapperRegistry;
import com.updownx.mybatisx.datasource.druid.DruidDataSourceFactory;
import com.updownx.mybatisx.mapping.Environment;
import com.updownx.mybatisx.mapping.MappedStatement;
import com.updownx.mybatisx.transaction.jdbc.JdbcTransactionFactory;
import com.updownx.mybatisx.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/** 配置项 */
public class Configuration {

  // 映射的语句，存在Map里
  protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();
  // 类型别名注册机
  protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
  // 环境
  protected Environment environment;
  // 映射注册机
  protected MapperRegistry mapperRegistry = new MapperRegistry(this);

  public Configuration() {
    typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
    typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
  }

  public void addMappers(String packageName) {
    mapperRegistry.addMappers(packageName);
  }

  public <T> void addMapper(Class<T> type) {
    mapperRegistry.addMapper(type);
  }

  public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
    return mapperRegistry.getMapper(type, sqlSession);
  }

  public boolean hasMapper(Class<?> type) {
    return mapperRegistry.hasMapper(type);
  }

  public void addMappedStatement(MappedStatement ms) {
    mappedStatements.put(ms.getId(), ms);
  }

  public MappedStatement getMappedStatement(String id) {
    return mappedStatements.get(id);
  }

  public TypeAliasRegistry getTypeAliasRegistry() {
    return typeAliasRegistry;
  }

  public Environment getEnvironment() {
    return environment;
  }

  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}

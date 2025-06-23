package com.updownx.mybatisx.session.defaults;

import com.updownx.mybatisx.session.Configuration;
import com.updownx.mybatisx.session.SqlSession;
import com.updownx.mybatisx.session.SqlSessionFactory;

/** 默认的 DefaultSqlSessionFactory */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

  private final Configuration configuration;

  public DefaultSqlSessionFactory(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public SqlSession openSession() {
    return new DefaultSqlSession(configuration);
  }
}

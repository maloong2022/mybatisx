package com.updownx.mybatisx.mapping;

import com.updownx.mybatisx.session.Configuration;
import com.updownx.mybatisx.type.JdbcType;

/** 参数映射 #{property,javaType=int,jdbcType=NUMERIC} */
public class ParameterMapping {

  private Configuration configuration;

  // property
  private String property;
  // javaType = int
  private Class<?> javaType = Object.class;
  // jdbcType=NUMERIC
  private JdbcType jdbcType;

  private ParameterMapping() {}

  public Configuration getConfiguration() {
    return configuration;
  }

  public String getProperty() {
    return property;
  }

  public Class<?> getJavaType() {
    return javaType;
  }

  public JdbcType getJdbcType() {
    return jdbcType;
  }

  public static class Builder {

    private final ParameterMapping parameterMapping = new ParameterMapping();

    public Builder(Configuration configuration, String property) {
      parameterMapping.configuration = configuration;
      parameterMapping.property = property;
    }

    public Builder javaType(Class<?> javaType) {
      parameterMapping.javaType = javaType;
      return this;
    }

    public Builder jdbcType(JdbcType jdbcType) {
      parameterMapping.jdbcType = jdbcType;
      return this;
    }
  }
}

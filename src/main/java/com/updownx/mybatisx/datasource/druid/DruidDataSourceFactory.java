package com.updownx.mybatisx.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.updownx.mybatisx.datasource.DataSourceFactory;
import java.util.Properties;
import javax.sql.DataSource;

/** Druid 数据源工厂 */
public class DruidDataSourceFactory implements DataSourceFactory {

  private Properties props;

  @Override
  public void setProperties(Properties props) {
    this.props = props;
  }

  @Override
  public DataSource getDataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(props.getProperty("driver"));
    dataSource.setUrl(props.getProperty("url"));
    dataSource.setUsername(props.getProperty("username"));
    dataSource.setPassword(props.getProperty("password"));
    return dataSource;
  }
}
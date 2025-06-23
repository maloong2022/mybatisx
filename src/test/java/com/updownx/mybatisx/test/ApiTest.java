package com.updownx.mybatisx.test;

import com.alibaba.fastjson.JSON;
import com.updownx.mybatisx.binding.MapperRegistry;
import com.updownx.mybatisx.builder.xml.XMLConfigBuilder;
import com.updownx.mybatisx.io.Resources;
import com.updownx.mybatisx.session.Configuration;
import com.updownx.mybatisx.session.SqlSession;
import com.updownx.mybatisx.session.SqlSessionFactory;
import com.updownx.mybatisx.session.SqlSessionFactoryBuilder;
import com.updownx.mybatisx.session.defaults.DefaultSqlSession;
import com.updownx.mybatisx.session.defaults.DefaultSqlSessionFactory;
import com.updownx.mybatisx.test.dao.IUserDao;
import com.updownx.mybatisx.test.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

public class ApiTest {
  private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

  @Test
  public void test_SqlSessionFactory() throws IOException {
    // 1. 从SqlSessionFactory中获取SqlSession
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
    SqlSession sqlSession = sqlSessionFactory.openSession();

    // 2. 获取映射器对象
    IUserDao userDao = sqlSession.getMapper(IUserDao.class);

    // 3. 测试验证
    User user = userDao.queryUserInfoById(1L);
    logger.info("测试结果：{}", JSON.toJSONString(user));
  }

  @Test
  public void test_selectOne() throws IOException {
    // 解析 XML
    Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
    XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
    Configuration configuration = xmlConfigBuilder.parse();

    // 获取 DefaultSqlSession
    SqlSession sqlSession = new DefaultSqlSession(configuration);

    // 执行查询：默认是一个集合参数
    Object[] req = {1L};
    Object res = sqlSession.selectOne("com.updownx.mybatisx.test.dao.IUserDao.queryUserInfoById", req);
    logger.info("测试结果：{}", JSON.toJSONString(res));
  }
}

package com.updownx.mybatisx.test;

import com.updownx.mybatisx.binding.MapperRegistry;
import com.updownx.mybatisx.io.Resources;
import com.updownx.mybatisx.session.SqlSession;
import com.updownx.mybatisx.session.SqlSessionFactory;
import com.updownx.mybatisx.session.SqlSessionFactoryBuilder;
import com.updownx.mybatisx.session.defaults.DefaultSqlSessionFactory;
import com.updownx.mybatisx.test.dao.IUserDao;
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
    Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    // 2. 获取映射器对象
    IUserDao userDao = sqlSession.getMapper(IUserDao.class);

    // 3. 测试验证
    String res = userDao.queryUserInfoById("10001");
    logger.info("测试结果：{}", res);
  }
}

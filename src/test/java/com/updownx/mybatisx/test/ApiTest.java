package com.updownx.mybatisx.test;

import com.updownx.mybatisx.binding.MapperRegistry;
import com.updownx.mybatisx.session.SqlSession;
import com.updownx.mybatisx.session.SqlSessionFactory;
import com.updownx.mybatisx.session.defaults.DefaultSqlSessionFactory;
import com.updownx.mybatisx.test.dao.IUserDao;
import org.junit.Test;

public class ApiTest {
  @Test
  public void test_MapperProxyFactory() {
    // 1. 注册 Mapper
    MapperRegistry registry = new MapperRegistry();
    registry.addMapper(IUserDao.class);

    // 2. 从 SqlSession 工厂获取 Session
    SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    // 3. 获取映射器对象
    IUserDao userDao = sqlSession.getMapper(IUserDao.class);

    // 4. 测试验证
    String res = userDao.queryUserName("10001");
    System.out.println(res);
  }
}

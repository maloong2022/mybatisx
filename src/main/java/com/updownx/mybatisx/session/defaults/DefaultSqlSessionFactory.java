package com.updownx.mybatisx.session.defaults;

import com.updownx.mybatisx.binding.MapperRegistry;
import com.updownx.mybatisx.session.SqlSession;
import com.updownx.mybatisx.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }

}
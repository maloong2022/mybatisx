package com.updownx.mybatisx.session;

import com.updownx.mybatisx.binding.MapperRegistry;
import com.updownx.mybatisx.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置项
 */
public class Configuration {
    /**
     * 映射的语句，存在 Map 里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();
    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    public void addMappers(String packageName){
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type){
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type,sqlSession);
    }

    public boolean hasMapper(Class<?> type){
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(),ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }
}

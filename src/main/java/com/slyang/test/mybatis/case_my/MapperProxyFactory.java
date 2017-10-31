package com.slyang.test.mybatis.case_my;

import org.apache.ibatis.binding.MapperMethod;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MapperProxyFactory<T>  {

	private final Class<T> mapperInterface;
	private final Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<Method, MapperMethod>();

	public MapperProxyFactory(Class<T> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

//	public T newInstance(SqlSession sqlSession) {
//		final MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, mapperInterface, methodCache);
//		return newInstance(mapperProxy);
//	}


}




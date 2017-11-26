package com.slyang.test.mybatis.usespring;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 拦截器，
 * 对update使用写库
 * 对query使用读库
 * <p>
 * 实现数据源切换
 */
@Intercepts({
		@Signature(
				type = Executor.class,
				method = "update",
				args = {MappedStatement.class, Object.class}),
		@Signature(
				type = Executor.class,
				method = "query",
				args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
		@Signature(
				type = Executor.class,
				method = "query",
				args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
})
public class MybatisWriteReaderPlugin implements org.apache.ibatis.plugin.Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		if (method.getName().equals("update")) {
			System.out.println("MybatisWriteReaderPlugin update");
			RouteDataSourceHolder.putDataSource(RouteDataSourceHolder.RouterDataSourceName.WRITE);
		}
		if (method.getName().equals("query")) {
			System.out.println("MybatisWriteReaderPlugin query");
			RouteDataSourceHolder.putDataSource(RouteDataSourceHolder.RouterDataSourceName.READ);
		}

		Object[] args = invocation.getArgs();
		for (Object arg : args) {
			if (arg instanceof MappedStatement) {
				MappedStatement statement = (MappedStatement) arg;
				System.out.println("MappedStatement id： " + statement.getId());
				String thispackage = statement.getId().substring(0, statement.getId().lastIndexOf("."));
				System.out.println("类名为： " + thispackage);
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor)
			return Plugin.wrap(target, this);
		return target;
	}

	@Override
	public void setProperties(Properties properties) {
	}

}

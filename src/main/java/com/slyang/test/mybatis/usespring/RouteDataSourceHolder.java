package com.slyang.test.mybatis.usespring;

public class RouteDataSourceHolder {

	private static final ThreadLocal<RouterDataSourceName> holder = new ThreadLocal<>();

	// 设置数据源
	public static void putDataSource(RouterDataSourceName dataSource) {
		holder.set(dataSource);
	}

	//获取数据源
	public static RouterDataSourceName getDataSource() {
		return holder.get();
	}

	// 清除数据源
	public static void clearDataSource() {
		holder.remove();
	}

	public enum RouterDataSourceName {
		READ, WRITE
	}

}

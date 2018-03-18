package com.slyang.test.mybatis.usespring;

public class RouteDataSourceHolder2 {

	private static final ThreadLocal<Integer> holder = new ThreadLocal<>();

	// 设置数据源
	public static void putDataSource(Integer dataSourceKey) {
		holder.set(dataSourceKey);
	}

	//获取数据源
	public static Integer getDataSource() {
		return holder.get();
	}

	// 清除数据源
	public static void clearDataSource() {
		holder.remove();
	}

}

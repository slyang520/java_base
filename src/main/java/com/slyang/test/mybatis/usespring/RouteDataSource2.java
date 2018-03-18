package com.slyang.test.mybatis.usespring;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RouteDataSource2 extends AbstractRoutingDataSource {

	Map<Integer, DataSource> dataSourceProvide = new ConcurrentHashMap<>();

	@Override
	public void afterPropertiesSet() {
		// todo insert db  dataSourceProvide  init

		super.afterPropertiesSet();
	}

	@Override
	protected DataSource determineTargetDataSource() {
		return super.determineTargetDataSource();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		Integer key = RouteDataSourceHolder2.getDataSource();
		if (key == null) {
			throw new NullPointerException();
		}
		return dataSourceProvide.get(key);
	}

}

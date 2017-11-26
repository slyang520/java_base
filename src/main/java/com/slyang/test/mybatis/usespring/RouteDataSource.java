package com.slyang.test.mybatis.usespring;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class RouteDataSource extends AbstractRoutingDataSource {

	private DataSource writeDataSource;
	private DataSource readDataSource;

	@Override
	public void afterPropertiesSet() {
		if (this.writeDataSource == null) {
			throw new IllegalArgumentException("Property 'writeDataSource' is required");
		}
		setDefaultTargetDataSource(writeDataSource);

		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(RouteDataSourceHolder.RouterDataSourceName.WRITE.name(), writeDataSource);
		targetDataSources.put(RouteDataSourceHolder.RouterDataSourceName.READ.name(), readDataSource);
		setTargetDataSources(targetDataSources);

		super.afterPropertiesSet();
	}

	@Override
	protected Object determineCurrentLookupKey() {

		RouteDataSourceHolder.RouterDataSourceName routerDataSourceName =
				RouteDataSourceHolder.getDataSource();

		if (routerDataSourceName == null
				|| routerDataSourceName == RouteDataSourceHolder.RouterDataSourceName.WRITE) {
			return RouteDataSourceHolder.RouterDataSourceName.WRITE.name();
		}

		return RouteDataSourceHolder.RouterDataSourceName.READ.name();
	}

	public DataSource getWriteDataSource() {
		return writeDataSource;
	}

	public void setWriteDataSource(DataSource writeDataSource) {
		this.writeDataSource = writeDataSource;
	}

	public DataSource getReadDataSource() {
		return readDataSource;
	}

	public void setReadDataSource(DataSource readDataSource) {
		this.readDataSource = readDataSource;
	}
}

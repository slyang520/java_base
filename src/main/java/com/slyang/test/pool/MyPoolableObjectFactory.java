package com.slyang.test.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by slyang on 17/6/12.
 */
public class MyPoolableObjectFactory extends BasePooledObjectFactory<Resource> {

	/**
	 * 创建一个对象实例
	 */
	@Override
	public Resource create() throws Exception {
		return new Resource();
	}

	/**
	 * 包裹创建的对象实例，返回一个pooledobject
	 */
	@Override
	public PooledObject<Resource> wrap(Resource obj) {
		return new DefaultPooledObject<Resource>(obj);
	}

	

}

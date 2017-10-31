package com.slyang.test.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by slyang on 17/6/12.
 */
public class ObjectPoolTest {

	//	ObjectPool、
	// 提供所有对象的存取管理。

	// PooledObject,
	// 池化的对象，是对对象的一个包装，加上了对象的一些其他信息，包括对象的状态（已用、空闲），对象的创建时间等。

	// PooledObjectFactory

	/**
	 * makeObject
	 */
	// 工厂类，负责池化对象的创建，对象的初始化，对象状态的销毁和对象状态的验证。
	public static void main(String[] arg) {

		// 创建池对象工厂
		PooledObjectFactory<Resource> factory = new MyPoolableObjectFactory();
		// 创建对象池
		final GenericObjectPool<Resource> pool = new GenericObjectPool<Resource>(factory, buildConfig());

		for (int i = 0; i < 40; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Resource resource = null;
					try {
						resource = pool.borrowObject();// 注意，如果对象池没有空余的对象，那么这里会block，可以设置block的超时时间
						System.out.println("  pool:borrow  " + resource + "###" + Thread.currentThread());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (resource != null) {
						pool.returnObject(resource);// 申请的资源用完了记得归还，不然其他人要申请时可能就没有资源用了
						System.out.println("  pool:return  " + resource + "###" + Thread.currentThread());
					}
				}
			}).start();
		}


	}

	private static GenericObjectPoolConfig buildConfig() {

		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		// 最大空闲数
		poolConfig.setMaxIdle(3);
		// 最小空闲数, 池中只有一个空闲对象的时候，池会在创建一个对象，并借出一个对象，从而保证池中最小空闲数为1
		poolConfig.setMinIdle(1);
		// 最大池对象总数
		poolConfig.setMaxTotal(4);
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		poolConfig.setMinEvictableIdleTimeMillis(1800000);
		// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		poolConfig.setTimeBetweenEvictionRunsMillis(1800000 * 2L);
		// 在获取对象的时候检查有效性, 默认false
		poolConfig.setTestOnBorrow(true);
		// 在归还对象的时候检查有效性, 默认false
		poolConfig.setTestOnReturn(false);
		// 在空闲时检查有效性, 默认false
		poolConfig.setTestWhileIdle(false);
		// 最大等待时间， 默认的值为-1，表示无限等待。
		// todo
		poolConfig.setMaxWaitMillis(3000);
		// 是否启用后进先出, 默认true
		poolConfig.setLifo(true);
		// 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		poolConfig.setBlockWhenExhausted(true);
		// 每次逐出检查时 逐出的最大数目 默认3
		poolConfig.setNumTestsPerEvictionRun(3);

		return poolConfig;

	}


}

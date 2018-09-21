package com.cs.lock;

import java.util.ArrayList;
import java.util.List;

public class ObjectPool {

	private int poolSize = 0;

	private List<Object> lockObjList;

	private int createdObjSize = 0;

	private PoolObjectFactory poolObjectFactory = null;

	public ObjectPool(int poolSize, PoolObjectFactory poolObjectFactory) {
		this.poolObjectFactory = poolObjectFactory;
		this.poolSize = poolSize;
		lockObjList = new ArrayList<Object>();
	}

	public int getPoolSize() {
		return this.poolSize;
	}

	public synchronized Object getObject() throws InterruptedException {

		Object rtnObj = null;
		while (true) {

			rtnObj = this.getLockObject();

			if (rtnObj != null)
				return rtnObj;
			else
				wait();

		}

	}

	private Object getLockObject() {

		if (this.poolSize <= 0)
			return this.poolObjectFactory.createPoolObject();

		if (lockObjList.size() > 0) {
			Object obj = this.lockObjList.get(0);
			this.lockObjList.remove(obj);
			return obj;
		}

		if (this.createdObjSize < this.poolSize) {
			this.createdObjSize++;
			return this.poolObjectFactory.createPoolObject();

		}
		return null;
	}

	public synchronized void releaseObject(Object obj) {

		if (this.poolSize <= 0)
			return;

		lockObjList.add(obj);

		this.notifyAll();

	}

}

package com.cs.lock;

public class TestPoolObjectFactory implements PoolObjectFactory {

	private int count = 0;

	@Override
	public Object createPoolObject() {

		count++;
		return new TestObject(count);
	}

}

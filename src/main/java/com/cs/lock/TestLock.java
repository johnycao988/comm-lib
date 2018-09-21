package com.cs.lock;

public class TestLock {

	public static void main(String[] args) {

		TestPoolObjectFactory tf = new TestPoolObjectFactory();

		ObjectPool op = new ObjectPool(5, tf);

		for (int i = 0; i < 20; i++) {

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						TestObject to = (TestObject) op.getObject();
						System.out.println(this.toString() + " Get Object-" + to.getCount() + " OK.");

						Thread.sleep(1000);

						op.releaseObject(to);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}, "Thread-Test-" + i).start();

		}

	}

}

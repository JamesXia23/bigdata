package cn.james.java.mythread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockTest implements Runnable{
	private static Lock lock = new ReentrantLock();//lock是一个接口,这个是他的一个实现类
	public static void main(String[] args) {
		new Thread(new MyLockTest()).start();
		new Thread(new MyLockTest()).start();
	}
	public void run() {
		// TODO Auto-generated method stub
		Thread t = Thread.currentThread();
		lock.lock();
		try {
			for (int i = 0; i < 100; i++) {
				System.out.println(t.getName() + ": " + i);
			}
		} finally {
			lock.unlock();
		}
		
	}
}

package cn.james.java.mythread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTryLock implements Runnable{
	private static Lock lock = new ReentrantLock();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new MyTryLock()).start();
		new Thread(new MyTryLock()).start();
	}

	public void run() {
		// TODO Auto-generated method stub
		Thread t = Thread.currentThread();
		//尝试拿锁,拿到了可以做同步的事,拿不到返回false,做其他事
		if(lock.tryLock()){
			try {
				for (int i = 0; i < 10; i++) {
					System.out.println(t.getName() + ": 我拿到锁了" + i);
				}
			} finally {
				// TODO: handle finally clause
				lock.unlock();
			}
		}else{
			System.out.println(t.getName() + ": 我没拿到锁");
		}
	}

}

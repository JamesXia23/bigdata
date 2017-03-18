package cn.james.java.mythread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockInterruptibly implements Runnable{
	private static Lock lock = new ReentrantLock();
	public static void main(String[] args) {
		Thread t1 = new Thread(new MyLockInterruptibly());
		Thread t2 = new Thread(new MyLockInterruptibly());
		t1.start();
		t2.start();
		try {
			Thread.sleep(2000);
			t2.interrupt();
			
		} catch (InterruptedException e) {
			System.out.println("主线程尝试吵醒t2失败");
		}
	}
	public void run() {
		// TODO Auto-generated method stub
		Thread t = Thread.currentThread();
		try {
			lock.lockInterruptibly();//无论有没有得到锁,该线程都可以被中断
			try {
				System.out.println(t.getName() + " :我得到了锁");
				Thread.sleep(10000);
			} finally {
				System.out.println("解锁");
				lock.unlock();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(t.getName() + " :我是被中断的线程");
		}
	}
}

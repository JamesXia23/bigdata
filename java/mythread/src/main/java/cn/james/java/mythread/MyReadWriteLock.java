package cn.james.java.mythread;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReadWriteLock implements Runnable{
	/**
	 * 另一个锁类,更加强大.读写锁
	 * 读锁可以无限制,所有线程可以一起获得,但是要获得写锁,要等所有线程都释放了锁才可以
	 */
	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new MyReadWriteLock()).start();
		new Thread(new MyReadWriteLock()).start();
	}
	public void run() {
		// TODO Auto-generated method stub
		Random r = new Random();
		for (int j = 0; j < 100; j++) {
			if(r.nextBoolean()){
				lock.writeLock().lock();
				try {
					int i = r.nextInt(100);
					System.out.println(Thread.currentThread().getName() + ": 获得写锁,写入了" + i);
					list.add(i);
				} finally {
					lock.writeLock().unlock();
				}
			}else{
				lock.readLock().lock();;
				try {
					if(list.size() == 0)
						System.out.println(Thread.currentThread().getName() + ": 数组没有元素");
					else{
						System.out.println(Thread.currentThread().getName() + ": 获得读锁,第一次读,数组最后一位" + list.get(list.size() - 1));				
						System.out.println(Thread.currentThread().getName() + ": 获得读锁,第二次读,数组最后一位" + list.get(list.size() - 1));				
						System.out.println(Thread.currentThread().getName() + ": 获得读锁,第三次读,数组最后一位" + list.get(list.size() - 1));										
					}
				} finally {
					lock.readLock().unlock();
				}
			}
		}
	}
}

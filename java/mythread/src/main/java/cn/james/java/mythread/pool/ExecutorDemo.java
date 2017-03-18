package cn.james.java.mythread.pool;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class ExecutorDemo {
	
	private ExecutorService newSingleThreadExecutor;
	private ExecutorService newCachedThreadPool;
	private ExecutorService newFixedThreadPool;
	private ScheduledExecutorService newSingleThreadScheduledExecutor;
	private ScheduledExecutorService newScheduledThreadPool;

	@Before
	public void init(){
		newSingleThreadExecutor = Executors.newSingleThreadExecutor();	//一个线程的线程池
		newCachedThreadPool = Executors.newCachedThreadPool();	//带缓冲的线程池,数量不定,如果线程超过60秒内没执行，那么将被终止并从池中删除
//		System.out.println(Runtime.getRuntime().availableProcessors());
		newFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());	//固定大小的线程池,Runtime.getRuntime().availableProcessors(),得到cpu个数
		newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(); 	//单线程可调度
		newScheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());	//固定大小可调度
	}
	@Test
	public void singleThreadTest() {
		for (int i = 0; i < 100; i++) {
			newSingleThreadExecutor.execute(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName());
				}
			});
			
		}
	}
	@Test
	public void cachedThreadTest() throws Exception {
		for (int i = 0; i < 100; i++) {
			newCachedThreadPool.execute(new Runnable(){
				public void run() {
					System.out.println(Thread.currentThread().getName());
					
				}
			});
			if(((i+1) % 25) == 0) {
				Thread.sleep(2000);
				System.out.println("-------------------------");
				Thread.sleep(65000);
				
			}
		}
	}
	@Test
	public void fixedThreadTest() throws Exception {
		for (int i = 0; i < 100; i++) {
			newFixedThreadPool.execute(new Runnable(){
				public void run() {
					System.out.println(Thread.currentThread().getName());
					
				}
			});
			if(((i+1) % 25) == 0) {
				Thread.sleep(2000);
				System.out.println("-------------------------");
				Thread.sleep(65000);
				
			}
		}
	}
	@Test
	public void singleThreadScheduledTest() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			newSingleThreadScheduledExecutor.schedule(new Runnable(){

				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName());
				}
				
			}, 4, TimeUnit.SECONDS);
			Thread.sleep(new Random().nextInt(5)*1000);
		}
	}
	@Test
	public void threadScheduledTest() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			newScheduledThreadPool.schedule(new Runnable(){

				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName());
				}
				
			}, 2, TimeUnit.SECONDS);
			Thread.sleep(new Random().nextInt(5)*1000);
		}
	}
	
	
	
}

package cn.james.java.mythread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ExecutorCallableDemo {
	private ScheduledExecutorService newScheduledThreadPool;

	public void init() {
		newScheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
	}
	
	public void textCallable() throws Exception, ExecutionException {
		ScheduledFuture<String> schedule = newScheduledThreadPool.schedule(new Callable<String>() {
			public String call() throws Exception {
				// TODO Auto-generated method stub
				return "my priority: " + Thread.currentThread().getPriority() + "";
			}
		}, 6, TimeUnit.SECONDS);
		System.out.println(schedule.get());
	}
}

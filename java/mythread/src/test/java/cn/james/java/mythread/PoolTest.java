package cn.james.java.mythread;

import org.junit.Before;
import org.junit.Test;

import cn.james.java.mythread.pool.ExecutorDemo;


public class PoolTest {
	private ExecutorDemo executorDemo;
	
	@Before
	public void init() {
		executorDemo = new ExecutorDemo();
		executorDemo.init();
	}
	
	@Test
	public void testPool() throws Exception {
//		executorDemo.singleThreadTest();
//		executorDemo.singleThreadScheduledTest();
//		executorDemo.cachedThreadTest();
//		executorDemo.fixedThreadTest();
		executorDemo.threadScheduledTest();
	}

}

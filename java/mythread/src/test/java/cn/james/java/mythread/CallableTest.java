package cn.james.java.mythread;

import org.junit.Before;
import org.junit.Test;

import cn.james.java.mythread.pool.ExecutorCallableDemo;

public class CallableTest {
	private ExecutorCallableDemo executorCallableDemo;
	
	@Before
	public void init() {
		executorCallableDemo = new ExecutorCallableDemo();
		executorCallableDemo.init();
	}
	
	
	@Test
	public void testCallable() throws Exception {
		executorCallableDemo.textCallable();
	}
}

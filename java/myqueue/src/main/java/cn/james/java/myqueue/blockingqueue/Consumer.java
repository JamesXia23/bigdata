package cn.james.java.myqueue.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable {
	LinkedBlockingQueue<String> linkedBlockingQueue;
	public Consumer(LinkedBlockingQueue<String> linkedBlockingQueue) {
		// TODO Auto-generated constructor stub
		this.linkedBlockingQueue = linkedBlockingQueue;
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}



}

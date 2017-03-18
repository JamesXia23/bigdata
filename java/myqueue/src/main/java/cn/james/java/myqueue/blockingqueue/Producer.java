package cn.james.java.myqueue.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable{
	LinkedBlockingQueue<String> linkedBlockingQueue;
	public Producer(LinkedBlockingQueue<String> linkedBlockingQueue) {
		this.linkedBlockingQueue = linkedBlockingQueue;
	}
	public void run() {
		// TODO Auto-generated method stub
		
	}



}

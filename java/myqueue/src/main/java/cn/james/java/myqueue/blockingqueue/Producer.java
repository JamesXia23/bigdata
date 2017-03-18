package cn.james.java.myqueue.blockingqueue;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable{
	LinkedBlockingQueue<String> linkedBlockingQueue;
	public Producer(LinkedBlockingQueue<String> linkedBlockingQueue) {
		this.linkedBlockingQueue = linkedBlockingQueue;
	}
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		Random random = new Random();
		while (true) {
			if(linkedBlockingQueue.offer(Thread.currentThread().getName() + "生产的物品" + i)) {
				i++;
				try {
					Thread.currentThread().sleep(random.nextInt(20) * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					Thread.currentThread().sleep(random.nextInt(3) * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}



}

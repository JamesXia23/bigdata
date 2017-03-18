package cn.james.java.myqueue.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	private static ArrayBlockingQueue<String> arrayBlockingQueue;
	private static LinkedBlockingQueue<String> linkedBlockingQueue;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		arrayBlockingQueue = new ArrayBlockingQueue<String>(10);
		linkedBlockingQueue = new LinkedBlockingQueue<String>();//链表队列可以设置长度,也可以不设置,如果不设置就是Integer.MAX_VALUE
		//3个生产者
		for (int i = 0; i < 3; i++) {
			new Thread(new Producer(linkedBlockingQueue), "生产者" + i).start();
		}
		for (int j = 0; j < 5; j++) {
			new Thread(new Consumer(linkedBlockingQueue), "消费者" + j).start();
		}
	}

}

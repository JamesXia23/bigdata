package cn.james.zk_distbute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZKClient {
	private ZooKeeper zk = null;
	private final String connectString = "node1:2181,node2:2181,node3:2181";
	private final int sessionTimeout = 6000;
	private final String serverNode = "/servers";
	private List<String> serverList = null;

	/**
	 * 连接zk
	 * 
	 * @param hostname
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws KeeperException
	 */
	private void connect() throws IOException, KeeperException, InterruptedException {
		zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

			public void process(WatchedEvent arg0) {
				// TODO Auto-generated method stub
				try {
					getChildren();
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 获取服务器上线
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	private void getChildren() throws KeeperException, InterruptedException{
		if (zk.exists(serverNode, false) != null) {
			List<String> children = zk.getChildren(serverNode, true);
			List<String> servers = new ArrayList<String>();
			for (String child : children) {
				servers.add(new String(zk.getData(serverNode + "/" + child, false, null)));
			}
			serverList = servers;
			System.out.println(servers);
		} else {
			System.out.println(serverNode + "不存在");
		}
	}
	/**
	 * 业务
	 * 
	 * @param hostname
	 * @throws InterruptedException
	 */
	private void bussiness() throws InterruptedException {
		System.out.println("client starting working......");
		Thread.sleep(Long.MAX_VALUE);
	}
	
	/*
	 * 1.连接zk,注册监听
	 * 2.获取服务器列表
	 * 3.业务
	 */
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZKClient client = new ZKClient();
		client.connect();
		client.getChildren();
		client.bussiness();
	}
}

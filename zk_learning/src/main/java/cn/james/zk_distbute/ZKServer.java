package cn.james.zk_distbute;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZKServer {
	private ZooKeeper zk = null;
	private final String connectString = "node1:2181,node2:2181,node3:2181";
	private final int sessionTimeout = 6000;
	private final String serverNode = "/servers";

	/**
	 * 连接zk
	 * 
	 * @param hostname
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws KeeperException
	 */
	private void connect(String hostname) throws IOException, KeeperException, InterruptedException {
		zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

			public void process(WatchedEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		if (zk.exists(serverNode, false) != null) {
			zk.create(serverNode + "/node", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		} else {
			zk.create(serverNode, "server".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create(serverNode + "/node", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		}
	}

	/**
	 * 业务
	 * 
	 * @param hostname
	 * @throws InterruptedException
	 */
	private void bussiness(String hostname) throws InterruptedException {
		System.out.println(hostname + " starting working......");
		Thread.sleep(Long.MAX_VALUE);
	}

	/*
	 * 1.服务器启动时注册信息 2.启动业务
	 */
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZKServer server = new ZKServer();

		server.connect(args[0]);
		server.bussiness(args[0]);
	}
}

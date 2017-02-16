package cn.james.zk_learning;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

public class SimpleZKClient {

	private static final String connectString = "node1:2181,node2:2181,node3:2181";
	private static final int sessionTimeout = 8000;// 相应时间,最好设置长一点,不然老是会超过时间
	private ZooKeeper zkCli = null;

	// public SimpleZKClient() throws IOException{
	// /**
	// * Watcher为默认监听器
	// */
	// zkCli = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
	// public void process(WatchedEvent arg0) {
	// System.out.println(arg0.getType() + "---" + arg0.getPath());
	// try {
	// zkCli.getChildren("/", true);
	// System.out.println("yes");
	// } catch (KeeperException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// System.out.println("no");
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// System.out.println("no fuck");
	// }
	// }
	// });
	// }
	@Before
	public void init() throws IOException {
		/**
		 * Watcher为默认监听器
		 */
		zkCli = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			public void process(WatchedEvent arg0) {
				System.out.println(arg0.getType() + "---" + arg0.getPath());
				try {
					zkCli.getChildren("/", true);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 创建节点
	 * 
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	@Test
	public void create() throws KeeperException, InterruptedException {
		/**
		 * 参数1:路径 参数2:节点数据,字节数组 参数3:节点权限,使用Ids枚举对象来指定
		 * 参数4:节点类型,使用CreateMode枚举对象来指定
		 */
		String path = zkCli.create("/eclipse", "hello eclipsezk".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
		System.out.println(path);
	}

	/**
	 * 查询节点,相当于ls
	 * 
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	@Test
	public void listNode() throws KeeperException, InterruptedException {
		// 第二个参数true表示监听,false表示不监听
		List<String> children = zkCli.getChildren("/", true);
		for (String child : children) {
			System.out.println(child);
		}
		Thread.sleep(Long.MAX_VALUE);// 注册一个监听,为了看到效果
	}

	/**
	 * 获取节点数据,相当于get
	 * 
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	@Test
	public void getData() throws KeeperException, InterruptedException {
		Stat stat = new Stat();// 节点的信息,如果不想获取界定啊信息,可以填null
		byte[] data = zkCli.getData("/eclipse", true, stat);
		System.out.println(new String(data));

	}

	/**
	 * 设置节点数据
	 * 
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	@Test
	public void setData() throws KeeperException, InterruptedException {
		if (zkCli.exists("/eclipse", false) != null) {
			Stat stat = zkCli.setData("/eclipse", "hello eclipse".getBytes(), -1);
			System.out.println(stat);
		} else {
			create();
		}
	}

	/**
	 * 删除节点
	 * 
	 * @throws InterruptedException
	 * @throws KeeperException
	 */
	@Test
	public void delete() throws InterruptedException, KeeperException {
		if (zkCli.exists("/eclipse", false) != null) {
			/**
			 * 参数2表示要删除的版本,-1代表全部版本
			 */
			zkCli.delete("/eclipse", -1);
			System.out.println("删除成功");
		} else {
			create();
			System.out.println("节点不存在,已经重新创建了一个");
		}
	}
}

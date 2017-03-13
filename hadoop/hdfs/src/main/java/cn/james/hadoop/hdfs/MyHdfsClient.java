package cn.james.hadoop.hdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Before;
import org.junit.Test;

public class MyHdfsClient {
	FileSystem fs = null;
	Configuration conf = null;
	@Before
	public void init() {
		conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://node1:9000");
		/**
		 * 这里的参数三是指定要上传文件用户名,必须跟要上传目录的所有者的名称相同,否则就要去改hdfs的文件夹权限
		 */
		try {
			fs = FileSystem.get(new URI("hdfs://node1:9000"), conf, "hadoop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void upload(){
		try {
			fs.copyFromLocalFile(new Path("/Users/jamesxia/Desktop/个人简历.pdf"), new Path("/"));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("文件系统关闭失败");
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void download() throws IllegalArgumentException, IOException {
		fs.copyToLocalFile(new Path("/wordcount/output/part-r-00000"), new Path("/Users/jamesxia/Desktop/wordcount.txt"));
		fs.close();
	}
	
	@Test
	public void listConf() {
		Iterator<Entry<String, String>> iterator = conf.iterator();
		
		for (Entry<String, String> entry : conf) {
			System.out.println(entry.getKey() + "-----" + entry.getValue());
		}
	}
	
	@Test
	public void list() {
		try {
			RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
			
			while(listFiles.hasNext()){
				LocatedFileStatus fileStatus = listFiles.next();
//				fileStatus.getPath().toString();
				System.out.println(fileStatus.getPath().toString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

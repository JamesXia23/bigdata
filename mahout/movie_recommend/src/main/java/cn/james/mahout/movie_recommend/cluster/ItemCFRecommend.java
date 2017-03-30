package cn.james.mahout.movie_recommend.cluster;

import java.net.URI;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;
import org.apache.mahout.cf.taste.hadoop.item.RecommenderJob;
public class ItemCFRecommend {

    private static final String HDFS = "hdfs://node1:9000";
	private static FileSystem hdfs;

    public static void main(String[] args) throws Exception {
    	Path localFile = new Path("/Users/jamesxia/Documents/NutStore/大四下/毕设/计软毕设/data/ml-1m/ratings.dat");
    	Path inPath = new Path(HDFS + "/user/hdfs/itemCF");
    	Path inFile = new Path(inPath + "/ratings.dat");
    	Path outPath = new Path(HDFS + "/user/hdfs/itemCF/result/");
    	Path outFile = new Path(outPath + "/part-r-00000");
    	Path tmpPath = new Path(HDFS + "/tmp/" + System.currentTimeMillis());

        JobConf conf = config();
        hdfs = FileSystem.get(new URI(HDFS), conf);
                
        if(!hdfs.exists(inPath)){
        	hdfs.mkdirs(inPath);
        }
        if(!hdfs.exists(inFile)){
    		hdfs.copyFromLocalFile(localFile, inPath);
        }
        if(hdfs.exists(outPath)){
        	hdfs.delete(outPath, true);
        }
//    	hdfs.listFiles(inPath, false);
//    	hdfs.cat(inFile);

        StringBuilder sb = new StringBuilder();
        sb.append("--input ").append(inPath);
        sb.append(" --output ").append(outPath);
        sb.append(" --booleanData false");
        sb.append(" --similarityClassname org.apache.mahout.math.hadoop.similarity.cooccurrence.measures.EuclideanDistanceSimilarity");
        sb.append(" --tempDir ").append(tmpPath);
        args = sb.toString().split(" ");

        RecommenderJob job = new RecommenderJob();
        job.setConf(conf);
        job.run(args);

//        hdfs.cat(outFile);
    }

    public static JobConf config() {
        JobConf conf = new JobConf(ItemCFRecommend.class);
        conf.setJobName("ItemCFRecommend");
        conf.set("fs.defaultFS", HDFS);
//        conf.addResource("classpath:/core-site.xml");
//        conf.addResource("classpath:/hdfs-site.xml");
//        conf.addResource("classpath:/mapred-site.xml");
//        conf.addResource("classpath:/yarn-site.xml");
//        conf.addResource("classpath:/log4j.properties");
        conf.setJarByClass(ItemCFRecommend.class);
        return conf;
    }
}
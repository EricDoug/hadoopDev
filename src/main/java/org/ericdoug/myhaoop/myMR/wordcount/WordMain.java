package org.ericdoug.myhaoop.myMR.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import org.ericdoug.myhaoop.myHDFS.*;



/**
 * Created by EricDoug on 14-11-10.
 */
public class WordMain {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        HdfsDAO hdfs = new HdfsDAO(conf);
        hdfs.rmr("test");
        hdfs.mkdirs("test");
        hdfs.upload("data/test.txt","test");
        hdfs.ls("test");
        hdfs.rmr("out");


//        String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
//
//        if(otherArgs.length != 2){
//            System.err.println("Usage : wordcount <in> <out>");
//            System.exit(2);
//        }
        Path in = new Path("hdfs://localhost:9000/user/playcrab/test/test.txt");
        Path out = new Path("hdfs://localhost:9000/user/playcrab/out");

        Job job = new Job(conf,"word count");
        job.setJarByClass(WordMain.class);
        job.setMapperClass(WordMapper.class);
        job.setCombinerClass(WordReducer.class);
        job.setReducerClass(WordReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
//        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
//        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        FileInputFormat.addInputPath(job, in);
        FileOutputFormat.setOutputPath(job,out);
        System.exit(job.waitForCompletion(true) ? 0:1);


    }
}

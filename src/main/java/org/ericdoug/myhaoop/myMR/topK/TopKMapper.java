package org.ericdoug.myhaoop.myMR.topK;

import basicLearn.StringTokenizerTest;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Created by EricDoug on 14-11-10.
 */
public class TopKMapper extends Mapper<Object,Text,IntWritable,Text> {
    IntWritable outKey = new IntWritable();
    Text outValue = new Text();

    public void map(Object key,Text value,Context context) throws IOException,InterruptedException{
        StringTokenizer st = new StringTokenizer(value.toString());
        while(st.hasMoreElements()){

        }
    }
}

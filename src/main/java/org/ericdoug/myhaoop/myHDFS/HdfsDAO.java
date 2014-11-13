package org.ericdoug.myhaoop.myHDFS;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.mapred.JobConf;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;


/**
 * Created by EricDoug on 14-11-10.
 */
public class HdfsDAO {
    public static final String HDFS = "hdfs://localhost:9000";
    private String hdfsPath;
    private Configuration conf;

    public HdfsDAO(Configuration conf){
        this(HDFS,conf);
    }

    public HdfsDAO(String hdfs,Configuration conf){
        this.hdfsPath = hdfs;
        this.conf = conf;
    }

    public static JobConf config() {
        JobConf conf = new JobConf(HdfsDAO.class);
        conf.setJobName("HdfsDAO");

        conf.addResource("classpath:/hadoop/core-site.xml");
        conf.addResource("classpath:/hadoop/hdfs-site.xml");
        conf.addResource("classpath:/hadoop/mapred-site.xml");
        return  conf;
    }

    public void mkdirs(String folder) throws IOException{
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath),conf);
        if( !fs.exists(path)){
            fs.mkdirs(path);
            System.out.println("Create: " + folder);
        }
        else{
            System.out.println(path.toString()+"is exists!");
        }
        fs.close();
    }

    public void rmr(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        fs.deleteOnExit(path);
        System.out.println("Delete: "+folder);
        fs.close();
    }

    public void rename(String src,String dst) throws IOException {
        Path name1 = new Path(src);
        Path name2 = new Path(dst);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        fs.rename(name1,name2);
        System.out.println("Rename: from "+src+" to "+dst);
        fs.close();
    }

    public void ls(String folder) throws IOException{
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        FileStatus[] list = fs.listStatus(path);
        System.out.println("ls: "+ folder);
        System.out.println("-------------------------------------------");
        for(FileStatus f : list){
            System.out.printf("name: %s,folder: %s,size: %d\n", f.getPath(), f.isDir(), f.getLen());
        }
        System.out.println("--------------------------------------------");
        fs.close();
    }

    public void createFile(String file,String content) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        byte[] buff = content.getBytes();
        FSDataOutputStream os = null;
        try{
            os = fs.create(new Path(file));
            os.write(buff,0,buff.length);
            System.out.println("Create: " + file);
        }finally {
            if(os != null)
                os.close();
        }
    }

    public void upload(String local,String remote) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(hdfsPath),conf);
        fs.copyFromLocalFile(new Path(local),new Path(remote));
        System.out.println("copy from: "+local+" to "+remote);
        fs.close();
    }

    public void download(String remote,String local) throws IOException{
        Path path = new Path(remote);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath),conf);
        fs.copyToLocalFile(path,new Path(local));
        System.out.println("download: from"+remote + " to "+local);
        fs.close();
    }

    public String cat(String remoteFile) throws IOException {
        Path path = new Path(remoteFile);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath),conf);
        FSDataInputStream fsdis = null;
        System.out.println("cat: " + remoteFile);

        OutputStream baos = new ByteArrayOutputStream();
        String str = null;
        try{
         fsdis = fs.open(path);
            IOUtils.copyBytes(fsdis,baos,4096,false);
            str = baos.toString();
        }finally {
            IOUtils.closeStream(fsdis);
            fs.close();
        }
        System.out.println(str);
        return str;
    }

    public void location() throws IOException {
        String folder = hdfsPath + "creeate/";
        String file = "t2.txt";
        FileSystem fs = FileSystem.get(URI.create(hdfsPath),conf);
        FileStatus f = fs.getFileStatus(new Path(folder + file));
        BlockLocation[] list = fs.getFileBlockLocations(f,0,f.getLen());
        System.out.println("File Location: "+folder+file);
        for(BlockLocation bl : list){
            String[] hosts = bl.getHosts();
            for(String host : hosts){
                System.out.println("hosts: "+ host);
            }
        }
        fs.close();
    }

    public static void main(String[] args) throws IOException {
        JobConf conf = config();
        HdfsDAO hdfs = new HdfsDAO(conf);
       // hdfs.rmr("test");
        //hdfs.mkdirs("test");
        //hdfs.upload("data/test.txt","test");
       // hdfs.ls("test");
        hdfs.ls("/user/playcrab/out");


    }


}

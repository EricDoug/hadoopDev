package basicLearn;

import java.io.*;
import java.text.ParseException;

/**
 * Created by EricDoug on 14-11-10.
 */
public class ParseData  {
    private String date;
    private String time;
    private String name;
    private String ip;

    private static ParseData parse(String line){
        System.out.println(line);
        ParseData parseData =new ParseData();
        String[] arr = line.split(" ");
        if(arr.length >= 3){
            parseData.setDate(arr[0]);
            parseData.setTime(arr[1]);
            parseData.setName(arr[2]);
            parseData.setIp(arr[3]);
        }
        return parseData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public static void main(String[] args) throws IOException{
//        InputStreamReader isr = null;
//        BufferedReader br = null;
//        try{
//            isr = new InputStreamReader(new FileInputStream("data/log.txt"),"UTF-8");
//            br = new BufferedReader(isr);
//            String data = br.readLine();
//            while(data != null){
//                System.out.println(data);
//                String[] arr = data.split(" ");
//                if(arr.length >= 3){
//                    System.out.println(arr[2]);
//                }
//                else{
//                    continue;
//                }
//                data = br.readLine();
//            }
//        }finally {
//            isr.close();
//            br.close();
//        }
        String line = "2011-10-26 06:11:35 ‘∆º∆À„ 210.77.23.1";
        System.out.println(line);
        ParseData parseData = new ParseData();
        String[] arr = line.split(" ");

        parseData.setDate(arr[0]);
        parseData.setTime(arr[1]);
        parseData.setName(arr[2]);
        parseData.setIp(arr[3]);

        System.out.println(parseData);

        try{
            System.out.println(parseData.getDate());
            System.out.println(parseData.getTime());
            System.out.println(parseData.getName());
            System.out.println(parseData.getIp());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

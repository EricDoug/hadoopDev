package basicLearn;

import java.util.StringTokenizer;

/**
 * Created by EricDoug on 14-11-10.
 */
public class StringTokenizerTest {
    public static void main(String[] args){
        String s = new String("The Java platform is the ideal platform for network computing");
        System.out.println(s);
        StringTokenizer st = new StringTokenizer(s);
        System.out.println("Token Total:"+st.countTokens());
        while (st.hasMoreElements()){
            System.out.println(st.nextToken());
        }

        String s2 = new String("The=Java=platform=is=the=ideal=platform=for=network=computing");
        System.out.println(s2);
        StringTokenizer st2 = new StringTokenizer(s2,"=",true);
        System.out.println("Token Total:"+st2.countTokens());
        while(st2.hasMoreElements()){
            System.out.println(st2.nextToken());
        }
    }
}

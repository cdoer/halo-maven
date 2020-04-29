package run.halo.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by yang 2020-04-29 2:19
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Map<String,String[]> qqqMap = new HashMap<String,String[]>();
        Map<String,String> aaaMap = new HashMap<String,String>();

        BufferedReader qqq = new BufferedReader(new InputStreamReader(Test.class.getResourceAsStream("qqq.txt")));
        BufferedReader aaa = new BufferedReader(new InputStreamReader(Test.class.getResourceAsStream("aaa.txt")));
        String line = null;
        while((line=qqq.readLine())!=null){
            String s = line.split(" ")[1].replaceAll("\"", "");
            String[] info  = s.split(":");
            qqqMap.put(s,info);
        }

        while((line=aaa.readLine())!=null){
            String[] info = line.replaceAll("[\"\\s]", "").split("=");
            aaaMap.put(info[0],info[1]);
        }

        StringBuilder sb = new StringBuilder();
        for (String s : qqqMap.keySet()) {
            sb.append("<dependency>\n");
            sb.append("\t<groupId>"+qqqMap.get(s)[0]+"</groupId>\n");
            sb.append("\t<artifactId>"+qqqMap.get(s)[1]+"</artifactId>\n");
            if(qqqMap.get(s).length==3){
                String s1 = aaaMap.get(qqqMap.get(s)[2].substring(1));
                if(s1!=null){
                    sb.append("\t<version>${"+qqqMap.get(s)[2].substring(1)+"}</version>\n");
                }
            }
            sb.append("</dependency>\n");
        }

        System.out.println(sb);
    }
}

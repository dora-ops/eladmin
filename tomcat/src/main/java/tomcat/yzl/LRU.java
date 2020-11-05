package tomcat.yzl;

import java.util.LinkedHashMap;

public class LRU {

    private LinkedHashMap map = new LinkedHashMap();


    public static void main(String[] args) {
        LinkedHashMap map = new LinkedHashMap();
        map.put("1","A");
        map.put("2","B");
        System.out.println(map.get("1"));
        System.out.println(map.values());
    }
}

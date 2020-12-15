package data;

import com.alibaba.fastjson.JSONObject;
import tomcat.yzl.FileUtil;
import tomcat.yzl.Main;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BodyGenerator {

    public static void main(String[] args) throws IOException {
        String json = FileUtil.getContentFromFile(Main.class.getClassLoader().getResourceAsStream("2.json"));
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject body = jsonObject.getJSONObject("body");
        Map<Boolean, List<JSONObject>> booleanListMap = BodyGeneratorUtil.generateYes(body);
        System.out.println(booleanListMap);
    }
}

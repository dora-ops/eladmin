package data;

import com.alibaba.fastjson.JSONObject;
import data.rule.StringGenerator;
import me.zhengjie.utils.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求体生成数据
 */
public class BodyGeneratorUtil {

    public static JSONObject generate(JSONObject json, boolean flag) {
        JSONObject genrateJson = new JSONObject();
        for (String key : json.keySet()) {
            Object value = json.get(key);
            if (value == null) {
                continue;
            }
            assert value != null;
            if (value instanceof String) {//1,9
                String str = (String) value;
                if (StringUtils.isBlank(str)) {
                    continue;
                }
                String[] split = str.split(",");
                int min = Integer.parseInt(split[0]);
                int max = Integer.parseInt(split[1]);
                String s = StringGenerator.generateStrFromTxt(min, max, flag);
                genrateJson.put(key, s);
            }
        }
        return genrateJson;
    }

    public static Map<Boolean, List<JSONObject>> generateYes(JSONObject json) {
        LinkedHashMap<Boolean, List<JSONObject>> result = new LinkedHashMap<>();
        List<JSONObject> yes = new ArrayList<>();
        List<JSONObject> no = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            yes.add(generate(json, true));
        }
        for (int i = 0; i < 3; i++) {
            no.add(generate(json, false));
        }
        result.put(true, yes);
        result.put(false, no);
        return result;
    }
}

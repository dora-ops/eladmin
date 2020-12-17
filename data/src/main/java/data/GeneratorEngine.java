package data;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class GeneratorEngine {

    public static int successCount = 1;

    public static int errorCount = 2;

    public static Map<Boolean, List<JSONObject>> generate(JSONObject json) {
        LinkedHashMap<Boolean, List<JSONObject>> result = new LinkedHashMap<>();
        List<JSONObject> yes = new ArrayList<>(successCount);
        List<JSONObject> no = new ArrayList<>(errorCount);
        for (int i = 0; i < successCount; i++) {
            yes.add(generate(json, true));
        }
        for (int i = 0; i < errorCount; i++) {
            no.add(generate(json, false));
        }
        result.put(true, yes);
        result.put(false, no);
        return result;
    }

    public static JSONObject generate(JSONObject json, boolean flag) {
        JSONObject genrateJson = new JSONObject();
        for (String key : json.keySet()) {
            String generator = json.getString(key);
            try {
                Class<?> aClass =  Thread.currentThread().getContextClassLoader().loadClass(generator);
                Generator g = Generator.class.cast(aClass.newInstance());
                Object generate = g.generate(flag);
                genrateJson.put(key, generate);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return genrateJson;
    }


}

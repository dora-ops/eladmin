package data.rule;


import data.Generator;
import lombok.Data;
import me.zhengjie.utils.FileUtil;

import java.io.IOException;
import java.util.*;

@Data
public class StringGenerator implements Generator {


    private int min = 3;

    private int max = 9;

    public static String generateStr(int min, int max, boolean flag) {
        Random random = new Random();
        int len = random.nextInt() * (max - min + 1);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(generateChar());
        }
        return builder.toString();
    }

    /**
     * 从文本随机取一个长度满足[min,max]之间的字符串
     *
     * @param min
     * @param max
     * @param flag
     * @return
     * @throws IOException
     */
    public static String generateStrFromTxt(int min, int max, boolean flag) {
        Random random = new Random();
        String contentFromFile = null;
        try {
            contentFromFile = FileUtil.getContentFromFile(StringGenerator.class.getClassLoader().getResourceAsStream("ran_str.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> list = Arrays.asList(contentFromFile.split(","));
        while (list.size() != 0) {
            int index = (int) (random.nextDouble() * list.size());
            String str = list.get(index);
            if (flag) {
                if (str.length() >= min && str.length() <= max) {
                    return str;
                }
                list = rm(list, index);
            } else {
                if (str.length() < min || str.length() > max) {
                    return str;
                }
                list = rm(list, index);
            }
        }
        throw new RuntimeException("未有文本满足");
    }

    private static List<String> rm(List<String> list, int index) {
        List<String> newList = new ArrayList<>();
        newList.addAll(list);
        newList.remove(index);
        return newList;
    }

    /**
     * 字母表生成
     *
     * @return
     */
    public static char generateChar() {
        //字母表生成
        char[] dic = new char[]{'a', 'b'};
        Random random = new Random();
        int index = random.nextInt() * (dic.length);
        return dic[index];
    }

    @Override
    public Object generate(Boolean json) {
        return generateStrFromTxt(min, max, json);
    }
}

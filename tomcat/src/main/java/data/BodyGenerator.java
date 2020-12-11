package data;

import tomcat.yzl.FileUtil;
import tomcat.yzl.Main;

import java.io.IOException;

public class BodyGenerator {

    public static void main(String[] args) throws IOException {
        String json = FileUtil.getContentFromFile(Main.class.getClassLoader().getResourceAsStream("2.json"));

    }
}

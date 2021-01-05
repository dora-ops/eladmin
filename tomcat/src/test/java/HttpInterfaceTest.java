import action.CSDNLogin;
import action.Login;
import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import me.zhengjie.utils.*;
import model.Graph;
import model.Node;
import model.TestNode;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import tomcat.yzl.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HttpInterfaceTest {


    public static boolean debug = false;

    public static String coockieStr = null;

    @Before
    public void before() throws IOException, UnirestException, InterruptedException, ClassNotFoundException {
        File stable_certificate = new File("stable_certificate");
        if (!stable_certificate.exists()) {
            stable_certificate.createNewFile();
        }

        File file = FileUtil.createFile("stable_coockie");
        Set<Cookie> cookies = (Set<Cookie>) FileUtil.getObjectFromFile(file);
        Properties properties = System.getProperties();
        properties.load(Main.class.getClassLoader().getResourceAsStream("stable.properties"));
        if (cookies == null) {

            Login demo = null;
            try {
                ChromeOptions options=new ChromeOptions();
                options.setExperimentalOption("debuggerAddress","127.0.0.1:14257");
                ChromeDriver driver=new ChromeDriver();
                demo = new CSDNLogin(driver);
                if (!debug) {
                    demo.login();
                    Thread.sleep(3000);
                }
                WebDriver.Options manage = driver.manage();
                cookies = manage.getCookies();
                coockieStr = CookieUtil.getHttpCoockieStr(cookies);
                FileOutputStream fileOutputStream = new FileOutputStream(new File("stable_certificate"));
                fileOutputStream.write(coockieStr.getBytes());
                fileOutputStream.close();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("stable_coockie")));
                objectOutputStream.writeObject(cookies);
                objectOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            coockieStr = CookieUtil.getHttpCoockieStr(cookies);
        }


    }

    @Test
    public void getGraph() throws IOException, SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String sql = FileUtil.getContentFromFile(Main.class.getClassLoader().getResourceAsStream("graph.sql"));
        ResultSet query = DBUtil.query(sql);
        List<Map<String, Object>> maps = JDBCDataUtil.convertList(query);
        for (Map<String, Object> map : maps) {
            Graph g = new Graph();
            BeanUtils.populate(g, map);
            sql = FileUtil.getContentFromFile(Main.class.getClassLoader().getResourceAsStream("1.sql"));
            query = DBUtil.query(sql);
            maps = JDBCDataUtil.convertList(query);
            LinkedList<TestNode> queue = CollectionUtil.getQueue(maps, "id", "parentId", 0, TestNode.class);
            for (Map<String, Object> node : maps) {
                Node n = new Node();
                TestNode testNode = new TestNode();
                BeanUtils.populate(testNode, node);
            }
        }
        String curl = "";
        String param = "";
        String referer = "";
        String post = HttpUtil.sendPost(curl, param, coockieStr, referer, "POST");
        System.out.println(post);
    }
}

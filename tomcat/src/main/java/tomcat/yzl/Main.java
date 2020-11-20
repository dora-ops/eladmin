package tomcat.yzl;

import java.io.*;
import java.util.Properties;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {

    public ChromeDriver driver;

    public static boolean debug = false;

    public Main() throws IOException {
       driver = new ChromeDriver();
    }

    public void login() {
        driver.get(System.getProperty("url"));
        System.out.println(driver.getWindowHandles());
        try {
            String title = driver.getTitle();
            loginOpt();

        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
        }
    }

    public void loginOpt() {
        driver.switchTo().frame("login-iframe");
        Actions action = new Actions(driver);
        By username = By.name("username");
        By userpass = By.name("userpass");
        By by = By.className("domain-body-submit-control");
        action.sendKeys(driver.findElement(username), System.getProperty("name")).perform();
        action.sendKeys(driver.findElement(userpass), System.getProperty("pass")).perform();
        action.moveToElement(driver.findElement(by));
        action.click();
        action.perform();
    }


    public static void main(String[] args) throws IOException, UnirestException, InterruptedException, ClassNotFoundException {

//        InputStream resourceAsStream = Main.class.getClassLoader().getResourceAsStream("certificate.properties");
        File stable_certificate = new File("stable_certificate");
        if (!stable_certificate.exists()){
            stable_certificate.createNewFile();
        }

        String coockieStr = null;
        File file = FileUtil.createFile("stable_coockie");
        Set<Cookie> cookies = (Set<Cookie>) FileUtil.getObjectFromFile(file);
        Properties properties = System.getProperties();
        properties.load(Main.class.getClassLoader().getResourceAsStream("stable.properties"));
        Cookie ctoken = null;
        if (cookies==null){

            Main demo = null;
            try {
                demo = new Main();
                if (!debug) {
                    demo.login();
                    Thread.sleep(3000);
                }
                WebDriver.Options manage = demo.driver.manage();
                cookies = manage.getCookies();
                coockieStr =  CookieUtil.getHttpCoockieStr(cookies);
                FileOutputStream fileOutputStream = new FileOutputStream(new File("stable_certificate"));
                fileOutputStream.write(coockieStr.getBytes());
                fileOutputStream.close();

//                certificate.store(new FileOutputStream(new File("certificate.properties")),"注释");
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(new File("stable_coockie")));
                objectOutputStream.writeObject(cookies);
                objectOutputStream.close();

                 ctoken = manage.getCookieNamed("ctoken");



            } catch (Exception e) {
                e.printStackTrace();

            }
        }else {
             coockieStr = CookieUtil.getHttpCoockieStr(cookies);
             ctoken = CookieUtil.getHttpCoockieStr(cookies, "ctoken");
        }
        String json = FileUtil.getContentFromFile(Main.class.getClassLoader().getResourceAsStream("1.json"));
        JSONObject jsonObject = JSONObject.parseObject(json);


        HttpResponse<String> response = HttpUtil.request(jsonObject,ctoken,coockieStr);
        System.out.println(response.getBody());
    }

}


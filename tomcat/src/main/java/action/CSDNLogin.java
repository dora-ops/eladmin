package action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CSDNLogin implements Login {

    public ChromeDriver driver=null;

    public CSDNLogin(ChromeDriver driver) {
        this.driver = driver;
    }

    @Override
    public void login() {



        driver.get(System.getProperty("url"));
        try {
            Actions action = new Actions(driver);
            WebElement element = driver.findElement(By.xpath("//a[contains(text(), \"账号密码登录\")]"));
            action.moveToElement(element).click().perform();

            By username = By.xpath("//input[@placeholder=\"手机号/邮箱/用户名\"]");
            By userpass = By.xpath("//input[@placeholder=\"密码\"]");
            By by = By.xpath("//button[contains(text(), \"登录\")]");
            action.sendKeys(driver.findElement(username), System.getProperty("name")).perform();
            action.sendKeys(driver.findElement(userpass), System.getProperty("pass")).perform();
            action.moveToElement(driver.findElement(by));
            action.click();
            action.perform();

        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
        }
    }
}

package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Set;

public class Base {

    protected static WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public int getWindowHandles(){
        return driver.getWindowHandles().size();
    }


    public void zoom(int percent) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + percent + "%'" );
        Thread.sleep(5000);
    }

    public void validPageURL(String expectedResult){
        String actualPageURL = driver.getCurrentUrl();
        Assert.assertEquals(actualPageURL, expectedResult);
    }

    public void navigate(String Elink, String Etitle, WebElement element) {
        // click on element it to open tab 2
        Actions action = new Actions(driver);
        action.moveToElement(element).click().perform();

        // Lấy danh sách các cửa sổ đang mở
        String currentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        // Chuyển đổi sang tab mới
        for (String window : windows) {
            if (!window.equals(currentWindow)){
                driver.switchTo().window(window);
            }
        }

        // Thực hiện các thao tác trên tab mới ở đây
        String Atitle = driver.getTitle();
        String Alink = driver.getCurrentUrl();

        validPageTitle(Etitle);
        validPageURL(Elink);
    }

    public void validPageTitle(String expectedResult){
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, expectedResult);
    }

}

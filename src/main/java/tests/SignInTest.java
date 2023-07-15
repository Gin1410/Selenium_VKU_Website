package tests;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;
import utils.ExcelHelpers;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignInTest {

    private WebDriver driver;
    private SignInPage signInPage = new SignInPage(driver);
    private HomePage homePage = new HomePage(driver);

    DataFormatter formatter = new DataFormatter();

    public ExcelHelpers excel() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("D:\\Tester\\Selenium\\VKU_Web\\VKU_DAOTAO.xlsx", "SignIn");
        return excel;
    }

    @AfterMethod
    public void teardown(){
        driver.close();
    }

    public void openPage(String URL){
        System.setProperty("webdriver.chrome.driver", "D:\\Tester\\Selenium\\VKU_Web\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        signInPage = new SignInPage(driver);
        homePage = new HomePage(driver);

        driver.get(URL);
    }

    @Test
    public void SignIn_case1() throws Exception {
        openPage(excel().getCellData("homePage","URL"));

        String expectedPageTitle = excel().getCellData("homePage","Title");
        signInPage.validPageTitle(expectedPageTitle);

        List<WebElement> navBarItems = homePage.navBarDropdown;

        for (WebElement navBarItem : navBarItems) {
            if (navBarItem.getText().equals("Đăng nhập")) {
                //CLick into ĐĂNG NHẬP
                navBarItem.click();
                Thread.sleep(500);

                List<WebElement> navBarSubmenu = driver.findElements(By.xpath("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[1]"));

                for (WebElement navBarSubmenuItem : navBarSubmenu) {
                    //Click into SINH VIÊN
                    if (navBarSubmenuItem.getText().equals("Sinh viên")) {
                        navBarSubmenuItem.click();

                        //check navigate true page
                        signInPage.validPageTitle(excel().getCellData("signInPage","Title"));

                        //click SignIn button
                        WebElement signInBtn = signInPage.SignInBtn.findElement(By.tagName("a"));
//                    WebElement signInBtn = driver.findElement(By.xpath("//body/div[@id='row']/div[2]/div[1]/div[1]/center[1]/button[1]/a[1]"));
                        signInBtn.click();

                        //check navigate true page
                        signInPage.validPageTitle(excel().getCellData("signInAcc","Title"));
                    }
                }
            }
        }
    }

    @Test
    public void SignIn_case2() throws Exception {
        openPage(excel().getCellData("signInAcc","URL"));
        String expectedPageTitle = excel().getCellData("signInAcc","Title");
        signInPage.validPageTitle(expectedPageTitle);
    }

    @Test
    public void SignIn_case3() throws Exception {
        SignIn_case1();
        WebElement email = signInPage.emailInput;

        String expectedEmailValue = excel().getCellData("email", "Value");
        Assert.assertTrue(email.getAttribute("aria-label").equals(expectedEmailValue));
    }

    @Test
    public void SignIn_case4() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case4","email"));

        Actions actions = new Actions(driver);
        actions.moveToElement(signInPage.nextBTN).click().perform();


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;

        String expectedPassValue = excel().getCellData("case4","Value");
        Assert.assertTrue(password.getAttribute("aria-label").equals(expectedPassValue));
    }

    @Test
    public void SignIn_case5() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case5","email"));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("case5","pass"));

        WebElement checkBox = signInPage.checkBox;

        Assert.assertTrue(password.getAttribute("type").equals("password"));
        Thread.sleep(1000);
        checkBox.click();
        Thread.sleep(1000);
        Assert.assertTrue(password.getAttribute("type").equals("text"));
    }

    @Test
    public void SignIn_case6() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case6", "email"));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("case6","pass"));

        Actions actions = new Actions(driver);
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        String expectedPageTitle = excel().getCellData("case6","Title");
        signInPage.validPageTitle(expectedPageTitle);

        WebElement userName = signInPage.userName;
        String expectedUserName = excel().getCellData("case6","userName");
        Assert.assertTrue(userName.getText().equals(expectedUserName));
    }

    @Test
    public void SignIn_case7() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());

        String expectedErrorGmail = excel().getCellData("case7","errorMsg");
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    //case 8
    @Test
    public void SignIn_case8() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case8","email"));
        mail.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());

        String expectedErrorGmail = excel().getCellData("case8","errorMsg");
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));

        Thread.sleep(1000);
    }

    //case 9
    @Test
    public void SignIn_case9() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case9","email"));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());
        String expectedErrorGmail = excel().getCellData("case9","errorMsg");
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void SignIn_case10() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case10","email"));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());

        String expectedErrorGmail = excel().getCellData("case10","errorMsg");
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void SignIn_case11() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case11","email"));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());

        String expectedErrorGmail = excel().getCellData("case11","errorMsg");
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void SignIn_case12() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;

        for(int i = 0; i < 10; i++){
            mail.sendKeys(Keys.BACK_SPACE);
        }

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());
        String expectedErrorGmail = excel().getCellData("case12","errorMsg");
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void SignIn_case13() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case13","email"));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(3000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());

        String expectedErrorGmail = excel().getCellData("case13","errorMsg");
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void SignIn_case14() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case14","email"));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());

        String expectedErrorGmail = excel().getCellData("case14","errorMsg");
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void SignIn_case15() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case15","email"));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());

        String expectedErrorGmail = excel().getCellData("case15","errorMsg");
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void SignIn_case16() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case16","email"));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorPass = signInPage.errorPass3;
        Assert.assertTrue(errorPass.isDisplayed());
        String expectedErrorPass = excel().getCellData("case16","errorMsg");

        System.out.println(errorPass.getText());

        Thread.sleep(500);
        Assert.assertTrue(errorPass.getText().equals(expectedErrorPass));
    }

    @Test
    public void SignIn_case17() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case17","email"));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;

        for (int i = 0; i < 10; i++) {
            password.sendKeys(Keys.BACK_SPACE);
        }

        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorPass = signInPage.errorPass3;
        Assert.assertTrue(errorPass.isDisplayed());
        String expectedErrorPass = excel().getCellData("case17","errorMsg");

        Thread.sleep(500);
        Assert.assertTrue(errorPass.getText().equals(expectedErrorPass));
    }

    @Test
    public void SignIn_case18() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case18","email"));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click   ();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("case18","pass"));
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorPass = signInPage.errorPass2;
        Assert.assertTrue(errorPass.isDisplayed());
        String expectedErrorPass = excel().getCellData("case18","errorMsg");

        Thread.sleep(500);
        Assert.assertTrue(errorPass.getText().equals(expectedErrorPass));
    }

    @Test
    public void SignIn_case19() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case19","email"));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click   ();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("case19","pass"));
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorPass = signInPage.errorPass2;
        Assert.assertTrue(errorPass.isDisplayed());
        String expectedErrorPass = excel().getCellData("case19","errorMsg");

        System.out.println(errorPass.getText());

        Thread.sleep(500);
        Assert.assertTrue(errorPass.getText().equals(expectedErrorPass));
    }

    @Test
    public void SignIn_case20() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case20","email"));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click   ();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("case20","pass"));
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorPass = signInPage.errorPass2;
        Assert.assertTrue(errorPass.isDisplayed());
        String expectedErrorPass = excel().getCellData("case20","errorMsg");

        Thread.sleep(500);
        Assert.assertTrue(errorPass.getText().equals(expectedErrorPass));
    }

    @Test
    public void SignIn_case21() throws Exception {
        SignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("case21","email"));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click();

        Thread.sleep(5000);

        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("case21","pass"));
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);


    }
}

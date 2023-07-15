package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ExcelHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageTest{

    private WebDriver driver;
    private HomePage homePage;

    public ExcelHelpers excel() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("D:\\Tester\\Selenium\\VKU_Web\\VKU_DAOTAO.xlsx", "HomePage");
        return excel;
    }
    public List<String> splitString(List<String> expect, String rowName, String colName) throws Exception {
        expect.add(excel().getCellData(rowName, colName));
        List<String> expectList = Arrays.asList(expect.get(0).split(","));
        return expectList;
    }


    @BeforeMethod
    public void setUp() {
        // Set up ChromeDriver executable path
        System.setProperty("webdriver.chrome.driver", "D:\\Tester\\Selenium\\VKU_Web\\chromedriver.exe");

        // Initialize ChromeDriver instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);

        // Navigate to the test website
        driver.get("https://daotao.vku.udn.vn/");
    }


    @AfterMethod
    public void tearDown() {
        // Quit ChromeDriver instance
        driver.quit();
    }


    @Test
    public void HP_case1() throws Exception {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("l").keyUp(Keys.CONTROL).build().perform();
        actions.sendKeys(Keys.ENTER).build().perform();

        homePage.validPageURL(excel().getCellData("URL","URL"));
        homePage.validPageTitle(excel().getCellData("URL","Title"));
    }

    @Test
    public void HP_case2() throws Exception {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.F5).perform();

        homePage.validPageURL(excel().getCellData("URL","URL"));
        homePage.validPageTitle(excel().getCellData("URL","Title"));
    }

    @Test
    public void HP_case3() throws InterruptedException {
        homePage.zoom(50);
    }

    @Test
    public void HP_case4() throws InterruptedException {
        homePage.zoom(150);
    }

    @Test
    public void HP_case5() throws InterruptedException {
        homePage.zoom(50);
        homePage.zoom(150);
        homePage.zoom(100);
    }

    @Test
    public void HP_case6() throws Exception {
        String AtopBarLeftBGColor = homePage.converColor(homePage.topBar.getCssValue("background-color"));
        String EtopBarLeftBGColor = excel().getCellData("topBarleft","BGColor");

        Assert.assertEquals(AtopBarLeftBGColor, EtopBarLeftBGColor);
    }

    @Test
    public void HP_case7() throws Exception {
        String EtopBarLeftItemColor =  excel().getCellData("topBarLeftItem","Color");
        String EtopBarLeftItemFontSize = excel().getCellData("topBarLeftItem","FontSize");

        for (WebElement item: homePage.topBarLeftItem){
            String AtopBarLeftItemColor = homePage.converColor(excel().getCellData("topBarLeftItem","Color"));
            String AtopBarLeftItemFontSize = homePage.topBarLeftItem.get(0).getCssValue("font-size");
            Assert.assertEquals(AtopBarLeftItemColor, EtopBarLeftItemColor);
            Assert.assertEquals(AtopBarLeftItemFontSize, EtopBarLeftItemFontSize);
        }
    }

    @Test
    public void HP_case8() throws Exception {
        int ANtopBarLeftItem = homePage.topBarLeftItem.size();
        int ENtopBarLeftItem = Integer.parseInt(excel().getCellData("topBarLeftItem","NO"));
        Assert.assertEquals(ANtopBarLeftItem, ENtopBarLeftItem);

        List<String> EtopBarLeftItem = new ArrayList<>();
        List<String> AtopBarLeftItem = new ArrayList<>();
        EtopBarLeftItem = splitString(EtopBarLeftItem, "topBarLeftItem","Item");

        for (WebElement item: homePage.topBarLeftItem){
            AtopBarLeftItem.add(item.getText());
        }

        Assert.assertEquals(AtopBarLeftItem, EtopBarLeftItem);
    }

    @Test
    public void HP_case9() throws Exception {
        for (WebElement item: homePage.topBarLeftItem){
            Actions actions = new Actions(driver);
            actions.moveToElement(item).perform();

            Assert.assertEquals(item.getCssValue("text-decoration"), excel().getCellData("topBarLeftItem","Text-decoration"));
            Assert.assertEquals(item.getCssValue("cursor"), "pointer");
        }
    }

    @Test
    public void HP_case10() throws Exception {
        String AtopBarRightBGColor = homePage.converColor(homePage.topBarRight.getCssValue("background-color"));
        String EtopBarRightBGColor = excel().getCellData("topBarRight","BGColor");

        Assert.assertEquals(AtopBarRightBGColor, EtopBarRightBGColor);
    }

    @Test
    public void HP_case11() throws Exception {
        String EtopBarRightItemColor = excel().getCellData("topBarRightItem","Color");
        String EtopBarRightItemFontSize = excel().getCellData("topBarRightItem","FontSize");
        String EtopBarRightItemBG = excel().getCellData("topBarRightItem","BGColor");
        String EtopBarRightItemBorder = excel().getCellData("topBarRightItem","Border");

        for(WebElement item : homePage.topBarRightItem){
            String AtopBarRightItemColor = homePage.converColor(item.getCssValue("color"));
            String AtopBarRightItemFontSize = item.getCssValue("font-size");
            String AtopBarRightItemBG = homePage.converColor(item.getCssValue("background-color"));
            String AtopBarRightItemBorder = item.getCssValue("border");

            Assert.assertEquals(AtopBarRightItemColor, EtopBarRightItemColor);
            Assert.assertEquals(AtopBarRightItemFontSize, EtopBarRightItemFontSize);
            Assert.assertEquals(AtopBarRightItemBG, EtopBarRightItemBG);
            Assert.assertEquals(AtopBarRightItemBorder, EtopBarRightItemBorder);
        }
    }

    @Test
    public void HP_case12() throws Exception {
        int ANtopBarRightItem = homePage.topBarRightItem.size();
        int ENtopBarRightItem = Integer.parseInt(excel().getCellData("topBarRightItem","NO"));
        Assert.assertEquals(ANtopBarRightItem, ENtopBarRightItem);
        List<String> EtopBarRightItem = new ArrayList<>();
        List<String> AtopBarRightItem = new ArrayList<>();
        EtopBarRightItem = splitString(EtopBarRightItem, "topBarRightItem","Item");
        for(WebElement item: homePage.topBarRightItem){
            AtopBarRightItem.add(item.getText());
        }

        Assert.assertEquals(AtopBarRightItem, EtopBarRightItem);
    }

    @Test
    public void HP_case13() throws Exception {
        String EtopBarRightItemHoverBG = excel().getCellData("topBarRightItemHover","BGColor");
        String EtopBarRightItemHoverColor = excel().getCellData("topBarRightItemHover","Border");

        for(WebElement item: homePage.topBarRightItem){
            Actions actions = new Actions(driver);
            actions.moveToElement(item).perform();

            String AtopBarRightItemHoverBG = homePage.converColor(item.getCssValue("background-color"));
            String AtopBarRightItemHoverColor = homePage.converColor(item.getCssValue("border-color"));

            Assert.assertEquals(AtopBarRightItemHoverBG, EtopBarRightItemHoverBG);
            Assert.assertEquals(AtopBarRightItemHoverColor, EtopBarRightItemHoverColor);
            Assert.assertEquals(item.getCssValue("cursor"), "pointer");
        }
    }

    @Test
    public void HP_case14(){
        int i =1;
        for (WebElement item: homePage.topBarRightItem){
            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            //check display subitem when hover item
            WebElement subMenu = driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/ul[1]/li[" + i + "]/ul"));
            Assert.assertTrue(subMenu.isDisplayed());

            i++;
        }
    }

    @Test
    public void HP_case15() throws Exception {
        //expected result
        List<String> eDT = new ArrayList<>();
        eDT = splitString(eDT, "doanThe","Item");
        List<String> eTT = new ArrayList<>();
        eTT = splitString(eTT, "trungTam","Item");
        List<String> eP = new ArrayList<>();
        eP = splitString(eP, "phong","Item");
        List<String> eK = new ArrayList<>();
        eK = splitString(eK, "khoa","Item");

        List<String> aDT = new ArrayList<>();
        List<String> aTT = new ArrayList<>();
        List<String> aP = new ArrayList<>();
        List<String> aK = new ArrayList<>();

        int i = 1;
        for (WebElement item : homePage.topBarRightItem) {

            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            WebElement subItem = driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/ul[1]/li[" + i + "]/ul"));

            List<WebElement> subItems = subItem.findElements(By.xpath("./li/a"));

            switch(i) {
                case 1:
                    for (WebElement sub : subItems){
                        aDT.add(sub.getText());
                    }
                    break;
                case 2:
                    for (WebElement sub : subItems){
                        aTT.add(sub.getText());
                    }
                    break;
                case 3:
                    for (WebElement sub : subItems){
                        aP.add(sub.getText());
                    }
                    break;
                case 4:
                    for (WebElement sub : subItems){
                        aK.add(sub.getText());
                    }
                    break;
            }
            i++;
        }

        Assert.assertEquals(aDT, eDT);
        Assert.assertEquals(aTT, eTT);
        Assert.assertEquals(aP, eP);
        Assert.assertEquals(aK, eK);
    }

    @Test
    public void HP_case16() throws Exception {
        String EtopBarRightSubBG = excel().getCellData("topBarRightSub","BGColor");

        int i =1;
        for (WebElement item: homePage.topBarRightItem){
            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            WebElement subMenu = driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/ul[1]/li[" + i + "]/ul"));

            String AtopBarRightSubBG = homePage.converColor(subMenu.getCssValue("background-color"));

            Assert.assertEquals(AtopBarRightSubBG, EtopBarRightSubBG);

            i++;
        }
    }

    @Test
    public void HP_case17() throws Exception {
        //expect
        String EtopbarRightSubHoverBG = excel().getCellData("topbarRightSubHover","BGColor");
        String EtopbarRightSubHoverColor = excel().getCellData("topbarRightSubHover","Color");
        String EtopbarRightSubHoverDecorate = excel().getCellData("topbarRightSubHover","Text-decoration");

        int i =1;
        for (WebElement item: homePage.topBarRightItem){
            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            WebElement subMenu = driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/ul[1]/li[" + i + "]/ul"));

            List<WebElement> subItems = subMenu.findElements(By.xpath("./li"));

            for (WebElement sub : subItems){
                Actions action1 = new Actions(driver);
                action1.moveToElement(sub).build().perform();

                WebElement subA = sub.findElement(By.xpath("./a"));

                String AtopbarRightSubHoverBG = homePage.converColor(sub.getCssValue("background-color"));
                String AtopbarRightSubHoverColor = homePage.converColor(subA.getCssValue("color"));
                String AtopbarRightSubHoverDecorate = subA.getCssValue("text-decoration");

                Assert.assertEquals(AtopbarRightSubHoverBG, EtopbarRightSubHoverBG);
                Assert.assertEquals(AtopbarRightSubHoverColor, EtopbarRightSubHoverColor);
                Assert.assertEquals(AtopbarRightSubHoverDecorate, EtopbarRightSubHoverDecorate);
                Assert.assertEquals(item.getCssValue("cursor"), "pointer");
            }
            i++;
        }
    }

    @Test
    public void HP_case18(){
        Actions action = new Actions(driver);
        action.moveToElement(homePage.logoImg).build().perform();

        Assert.assertEquals(homePage.logoImg.getCssValue("cursor"), "pointer");
    }

    @Test
    public void HP_case19() throws Exception {
        String EtranslateText = excel().getCellData("translateIMG","Item");
        String AtranslateText = homePage.translateImg.getText();

        Assert.assertEquals(AtranslateText, EtranslateText);
    }

    @Test
    public void HP_case20(){
        Actions action = new Actions(driver);
        action.moveToElement(homePage.translateImg).build().perform();

        Assert.assertEquals(homePage.translateImg.getCssValue("cursor"), "pointer");
    }

    @Test
    public void HP_case21() throws Exception {
        String EnavBarBG = excel().getCellData("navBar","BGColor");
        String AnavBarBG = homePage.converColor(homePage.navBar.getCssValue("background-color"));

        Assert.assertEquals(AnavBarBG, EnavBarBG);
    }

    @Test
    public void HP_case22() throws Exception {
        int ANnavBarDropdown = homePage.navBarDropdown.size();
        int ENnavBarDropdown = Integer.parseInt(excel().getCellData("navBarDropdown","NO"));
        Assert.assertEquals(ANnavBarDropdown, ENnavBarDropdown);

        List<String> EnavBarDropdown = new ArrayList<>();
        List<String> AnavBarDropdown = new ArrayList<>();
        EnavBarDropdown = splitString(EnavBarDropdown, "navBarDropdown","Item");
        for(WebElement item: homePage.navBarDropdown){
            AnavBarDropdown.add(item.getText());
        }

        Assert.assertEquals(AnavBarDropdown, EnavBarDropdown);
    }

    @Test
    public void HP_case23() throws Exception {
        String EnavBarTextColor = excel().getCellData("navBarDropdown","Color");
        String EnavBarTextFontSize = excel().getCellData("navBarDropdown","FontSize");
        for(WebElement item: homePage.navBarDropdown){
            item = item.findElement(By.xpath("./a"));

            String AnavBarTextColor = homePage.converColor(item.getCssValue("color"));
            String AnavBarTextFontSize = item.getCssValue("font-size");

            Assert.assertEquals(AnavBarTextColor, EnavBarTextColor);
            Assert.assertEquals(AnavBarTextFontSize, EnavBarTextFontSize);
        }
    }

    @Test
    public void HP_case24() throws Exception {
        String EnavBarDropdownHoverBG = excel().getCellData("navBarDropdownHover","BGColor");
        String EnavBarDropdownHoverColor = excel().getCellData("navBarDropdownHover","Color");

        for(WebElement item: homePage.navBarDropdown){
            item = item.findElement(By.xpath("./a"));

            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            String AnavBarHoverBG = homePage.converColor(item.getCssValue("background-color"));
            String AnavBarHoverColor = homePage.converColor(item.getCssValue("color"));

            Assert.assertEquals(AnavBarHoverBG, EnavBarDropdownHoverBG);
            Assert.assertEquals(AnavBarHoverColor, EnavBarDropdownHoverColor);
            Assert.assertEquals(item.getCssValue("cursor"), "pointer");
        }
    }

    @Test
    public void HP_case25() throws Exception {
        String EnavBarDropdownMenuBG = excel().getCellData("navBarDropdownMenu","BGColor");
        String EnavBarDropdownMenuColor = excel().getCellData("navBarDropdownMenu","Color");

        for(WebElement item: homePage.navBarDropdown){
            Actions action = new Actions(driver);
            action.moveToElement(item).click().build().perform();

            for (WebElement subitem : homePage.navBarDropdownMenu){
                String AnavBarDropdownMenuBG = homePage.converColor(subitem.getCssValue("background-color"));
                String AnavBarDropdownMenuColor = homePage.converColor(subitem.getCssValue("color"));

                Assert.assertEquals(AnavBarDropdownMenuBG, EnavBarDropdownMenuBG);
                Assert.assertEquals(AnavBarDropdownMenuColor, EnavBarDropdownMenuColor);
            }
        }
    }

    @Test
    public void HP_case26() throws Exception {
        List<String> AnavBarSub = new ArrayList<>();
        List<String> EnavBarSub = new ArrayList<>();

        EnavBarSub.add(excel().getCellData("navBarSub","Item"));
        EnavBarSub = Arrays.asList(EnavBarSub.get(0).split("\\n"));

        for (WebElement item: homePage.navBarDropdown){
            List<WebElement>subNavBar = item.findElements(By.xpath("./ul/li"));
            AnavBarSub.add(item.getText());
            AnavBarSub.add(String.valueOf(subNavBar.size()));

            Actions action = new Actions(driver);
            action.moveToElement(item).click().build().perform();

            for (WebElement subitem : subNavBar){
                AnavBarSub.add(subitem.getText());
            }
        }
        Assert.assertEquals(AnavBarSub,EnavBarSub);
    }

    @Test
    public void HP_case27() throws Exception {
        String EnavBarSubHoverBG = excel().getCellData("navBarSubHover","BGColor");
        String EnavBarSubHoverT = excel().getCellData("navBarSubHover","Color");

        for (WebElement item: homePage.navBarDropdown){
            List<WebElement>subNavBar = item.findElements(By.xpath("./ul/li/a"));
            Actions action = new Actions(driver);
            action.moveToElement(item).click().build().perform();

            for (WebElement subitem : subNavBar){
                Actions actionSub = new Actions(driver);
                actionSub.moveToElement(subitem).build().perform();

                String AnavBarSubHoverBG = homePage.converColor(subitem.getCssValue("background-color"));
                String AnavBarSubHoverT = homePage.converColor(subitem.getCssValue("color"));

                Assert.assertEquals(AnavBarSubHoverBG, EnavBarSubHoverBG);
                Assert.assertEquals(AnavBarSubHoverT, AnavBarSubHoverT);
            }
        }
    }

    @Test
    public void HP_case28() throws Exception {
        String EfieldsetBG = excel().getCellData("fieldset","BGColor");
        String EfieldsetBorder = excel().getCellData("fieldset","Border");

        WebElement fieldset = homePage.searchBox.findElement(By.xpath("./form/fieldset"));

        String AfieldsetBG = homePage.converColor(fieldset.getCssValue("background-color"));
        String AfieldsetBorder = homePage.converColor(fieldset.getCssValue("border-color"));

        Assert.assertEquals(AfieldsetBG, EfieldsetBG);
        Assert.assertEquals(AfieldsetBorder, EfieldsetBorder);
    }

    @Test
    public void HP_case29(){
        Assert.assertEquals(homePage.searchBtn.getCssValue("cursor"), "pointer");
    }

    @Test
    public void HP_case30(){
        WebElement sideBarA = homePage.sideBar.findElement(By.xpath("./a"));
        Assert.assertEquals(sideBarA.getCssValue("cursor"), "pointer");
    }

    @Test
    public void HP_case31() throws Exception {
        WebElement title = homePage.sideBarList.findElement(By.xpath("./h2"));

        String AsideBarTitleBG = homePage.converColor(title.getCssValue("background-color"));
        String AsideBarTitleT = homePage.converColor(title.getCssValue("color"));
        String AsideBarTitleFS = title.getCssValue("font-size");

        String EsideBarTitleBG = excel().getCellData("sideBarTitle","BGColor");
        String EsideBarTitleT = excel().getCellData("sideBarTitle", "Color");
        String EsideBarTitleFS = excel().getCellData("sideBarTitle", "FontSize");

        Assert.assertEquals(AsideBarTitleBG, EsideBarTitleBG);
        Assert.assertEquals(AsideBarTitleT, EsideBarTitleT);
        Assert.assertEquals(AsideBarTitleFS, EsideBarTitleFS);
    }

    @Test
    public void HP_case32(){
    }

    @Test
    public void HP_case33() throws Exception {
        for (WebElement item: homePage.sideBarListItem){
            WebElement a = item.findElement(By.xpath("./td/a"));

            String AsideBarContentT = homePage.converColor(item.getCssValue("color"));
            String AsideBarContentFS = item.getCssValue("font-size");

            String EsideBarContentT = excel().getCellData("sideBarContent", "Color");
            String EsideBarContentFS = excel().getCellData("sideBarContent", "FontSize");

            Assert.assertEquals(AsideBarContentT, EsideBarContentT);
            Assert.assertEquals(AsideBarContentFS, EsideBarContentFS);
        }
    }
    @Test
    public void HP_case34() throws Exception {
        for (WebElement item: homePage.sideBarListItem){
            WebElement a = item.findElement(By.xpath("./td"));

            Actions actions = new Actions(driver);
            actions.moveToElement(a).build().perform();

            String AsideBarContentHoverT = homePage.converColor(item.getCssValue("color"));
            String AsideBarContentHoverD = item.getCssValue("text-decoration");

            String EsideBarContentHoverT = excel().getCellData("sideBarContentHover", "Color");
            String EsideBarContentHoverD = excel().getCellData("sideBarContentHover", "Text-decoration");

            Assert.assertEquals(AsideBarContentHoverT, EsideBarContentHoverT);
            Assert.assertEquals(AsideBarContentHoverD, EsideBarContentHoverD);
            Assert.assertEquals(a.getCssValue("cursor"), "pointer");        }
    }
    @Test
    public void HP_case35(){

    }
    @Test
    public void HP_case36(){

    }
    @Test
    public void HP_case37(){

    }
    @Test
    public void HP_case38(){

    }
    @Test
    public void HP_case39(){

    }
    @Test
    public void HP_case40(){

    }
    @Test
    public void HP_case41(){

    }    @Test
    public void HP_case42(){

    }
    @Test
    public void HP_case43() throws Exception {
        // Tạo đối tượng WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Chờ đợi cho đến khi phần tử được load hoàn toàn
        wait.until(ExpectedConditions.visibilityOf(homePage.footer));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homePage.footer);

        String EfooterBG = excel().getCellData("footer","BGColor");

        String AfooterBG = homePage.converColor(homePage.footer.getCssValue("background-color"));

        Assert.assertEquals(AfooterBG, EfooterBG);
    }

    @Test
    public void HP_case44() throws Exception {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("l").keyUp(Keys.CONTROL).build().perform();
        actions.keyDown(Keys.DELETE).keyUp(Keys.DELETE).build().perform();

        homePage.validPageURL(excel().getCellData("URL","URL"));
        homePage.validPageTitle(excel().getCellData("URL","Title"));
    }

    @Test
    public void HP_case45() throws Exception {
        String EsearchBoxPl = excel().getCellData("searchBox","Placeholder");
        String AsearchBoxPl = homePage.searchInput.getAttribute("placeholder");

        Assert.assertTrue(AsearchBoxPl.contains(EsearchBoxPl));

        Actions actions = new Actions(driver);
        actions.moveToElement(homePage.searchInput).click().build().perform();

        String AsearchBoxPlClick = homePage.searchInput.getAttribute("placeholder");

        Assert.assertTrue(AsearchBoxPlClick.contains(EsearchBoxPl));

    }

    @Test
    public void HP_case46() throws Exception {
        String Elink = excel().getCellData("tinTucSuKien","URL");
        String Etitle = excel().getCellData("tinTucSuKien","Title");
        WebElement element = homePage.topBar.findElement(By.xpath("./div[1]/div[1]/a[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case47() throws Exception {
        String Elink = excel().getCellData("thongBao","URL");
        String Etitle = excel().getCellData("thongBao","Title");
        WebElement element = homePage.topBar.findElement(By.xpath("./div[1]/div[1]/a[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case48() throws Exception {
        String Elink = excel().getCellData("danhBa","URL");
        String Etitle = excel().getCellData("danhBa","Title");
        WebElement element = homePage.topBar.findElement(By.xpath("./div[1]/div[1]/a[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case49() throws Exception {
        String Elink = excel().getCellData("bieuMau","URL");
        String Etitle = excel().getCellData("bieuMau","Title");
        WebElement element = homePage.topBar.findElement(By.xpath("./div[1]/div[1]/a[4]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case50() throws Exception {
        String Elink = excel().getCellData("lichTuan","URL");
        String Etitle = excel().getCellData("lichTuan","Title");
        WebElement element = homePage.topBar.findElement(By.xpath("./div[1]/div[1]/a[5]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case51() throws Exception {
        String Elink = excel().getCellData("hinhAnh","URL");
        String Etitle = excel().getCellData("hinhAnh","Title");
        WebElement element = homePage.topBar.findElement(By.xpath("./div[1]/div[1]/a[6]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case52() throws Exception {
        String Elink = excel().getCellData("email","URL");
        String Etitle = excel().getCellData("email","Title");
        WebElement element = homePage.topBar.findElement(By.xpath("./div[1]/div[1]/a[7]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case53() throws Exception {
        String Elink = excel().getCellData("RMP01","URL");
        String Etitle = excel().getCellData("RMP01","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case54() throws Exception {
        String Elink = excel().getCellData("RMP02","URL");
        String Etitle = excel().getCellData("RMP02","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case55() throws Exception {
        String Elink = excel().getCellData("RMP03","URL");
        String Etitle = excel().getCellData("RMP03","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case56() throws Exception {
        String Elink = excel().getCellData("RMP04","URL");
        String Etitle = excel().getCellData("RMP04","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/ul/li[4]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case57() throws Exception {
        String Elink = excel().getCellData("RMP05","URL");
        String Etitle = excel().getCellData("RMP05","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/ul/li[5]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case58() throws Exception {
        String Elink = excel().getCellData("RMP06","URL");
        String Etitle = excel().getCellData("RMP06","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/ul/li[6]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case59() throws Exception {
        String Elink = excel().getCellData("RMP07","URL");
        String Etitle = excel().getCellData("RMP07","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/ul/li[7]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case60() throws Exception {
        String Elink = excel().getCellData("RMP08","URL");
        String Etitle = excel().getCellData("RMP08","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[3]/ul/li[8]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case61() throws Exception {
        String Elink = excel().getCellData("RMTT01","URL");
        String Etitle = excel().getCellData("RMTT01","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[2]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[2]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case62() throws Exception {
        String Elink = excel().getCellData("RMTT02","URL");
        String Etitle = excel().getCellData("RMTT02","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[2]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[2]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case63() throws Exception {
        String Elink = excel().getCellData("RMTT03","URL");
        String Etitle = excel().getCellData("RMTT03","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[2]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[2]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case64() throws Exception {
        String Elink = excel().getCellData("RMTT04","URL");
        String Etitle = excel().getCellData("RMTT04","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[2]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[2]/ul/li[4]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }


    @Test
    public void HP_case65() throws Exception {
        String Elink = excel().getCellData("RMDT01","URL");
        String Etitle = excel().getCellData("RMDT01","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[1]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[1]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case66() throws Exception {
        String Elink = excel().getCellData("RMDT02","URL");
        String Etitle = excel().getCellData("RMDT02","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[1]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[1]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case67() throws Exception {
        String Elink = excel().getCellData("RMDT03","URL");
        String Etitle = excel().getCellData("RMDT03","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[1]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[1]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case68() throws Exception {
        String Elink = excel().getCellData("RMK01","URL");
        String Etitle = excel().getCellData("RMK01","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[4]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[4]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case69() throws Exception {
        String Elink = excel().getCellData("RMK02","URL");
        String Etitle = excel().getCellData("RMK02","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[4]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[4]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case70() throws Exception {
        String Elink = excel().getCellData("RMK03","URL");
        String Etitle = excel().getCellData("RMK03","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[4]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[4]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case71() throws Exception {
        String Elink = excel().getCellData("RMK04","URL");
        String Etitle = excel().getCellData("RMK04","Title");

        WebElement P = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[4]/a[1]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(P).click().build().perform();

        WebElement element = homePage.topBarRight.findElement(By.xpath("./ul[1]/li[4]/ul/li[4]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case72() throws Exception {
        String Elink = excel().getCellData("logoIMG","URL");
        String Etitle = excel().getCellData("logoIMG","Title");

        WebElement element = homePage.logoImg;
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case73() throws Exception {
        String Elink = excel().getCellData("translateIMG","URL");
        String Etitle = excel().getCellData("translateIMG","Title");

        WebElement element = homePage.translateImg;
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case74() throws Exception {
        String Elink = excel().getCellData("NBGT01","URL");
        String Etitle = excel().getCellData("NBGT01","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[1]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case75() throws Exception {
        String Elink = excel().getCellData("NBGT02","URL");
        String Etitle = excel().getCellData("NBGT02","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[1]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case76() throws Exception {
        String Elink = excel().getCellData("NBGT03","URL");
        String Etitle = excel().getCellData("NBGT03","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[1]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case77() throws Exception {
        String Elink = excel().getCellData("NBGT04","URL");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[1]/ul/li[4]"));
        homePage.navigate(Elink, "", element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case78() throws Exception {
        String Elink = excel().getCellData("NBTB01","URL");
        String Etitle = excel().getCellData("NBTB01","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[2]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[2]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case79() throws Exception {
        String Elink = excel().getCellData("NBTB02","URL");
        String Etitle = excel().getCellData("NBTB02","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[2]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[2]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case80() throws Exception {
        String Elink = excel().getCellData("NBTB03","URL");
        String Etitle = excel().getCellData("NBTB03","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[2]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[2]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case81() throws Exception {
        String Elink = excel().getCellData("NBTKB01","URL");
        String Etitle = excel().getCellData("NBTKB01","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[3]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[3]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case82() throws Exception {
        String Elink = excel().getCellData("NBTKB02","URL");
        String Etitle = excel().getCellData("NBTKB02","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[3]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[3]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case83() throws Exception {
        String Elink = excel().getCellData("NBTKB03","URL");
        String Etitle = excel().getCellData("NBTKB03","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[3]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[3]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case84() throws Exception {
        String Elink = excel().getCellData("NBTKB04","URL");
        String Etitle = excel().getCellData("NBTKB04","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[3]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[3]/ul/li[4]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case85() throws Exception {
        String Elink = excel().getCellData("NBTKB05","URL");
        String Etitle = excel().getCellData("NBTKB05","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[3]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[3]/ul/li[5]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case86() throws Exception {
        String Elink = excel().getCellData("NBQT1","URL");
        String Etitle = excel().getCellData("NBQT1","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[4]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[4]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case87() throws Exception {
        String Elink = excel().getCellData("NBQT2","URL");
        String Etitle = excel().getCellData("NBQT2","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[4]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[4]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case88() throws Exception {
        String Elink = excel().getCellData("NBQT3","URL");
        String Etitle = excel().getCellData("NBQTB3","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[4]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[4]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case89() throws Exception {
        String Elink = excel().getCellData("NBCTDT1","URL");
        String Etitle = excel().getCellData("NBCTDT1","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[5]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[5]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case90() throws Exception {
        String Elink = excel().getCellData("NBCTDT2","URL");
        String Etitle = excel().getCellData("NBCTDT2","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[5]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[5]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case91() throws Exception {
        String Elink = excel().getCellData("NBCTDT3","URL");
        String Etitle = excel().getCellData("NBCTDT3","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[5]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[5]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case92() throws Exception {
        String Elink = excel().getCellData("NBCTDT4","URL");
        String Etitle = excel().getCellData("NBCTDT4","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[5]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[5]/ul/li[4]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case93() throws Exception {
        String Elink = excel().getCellData("NBL1","URL");
        String Etitle = excel().getCellData("NBL1","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[6]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[6]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case94() throws Exception {
        String Elink = excel().getCellData("NBL2","URL");
        String Etitle = excel().getCellData("NBL2","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[6]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[6]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case95() throws Exception {
        String Elink = excel().getCellData("NBL3","URL");
        String Etitle = excel().getCellData("NBL3","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[6]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[6]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case96() throws Exception {
        String Elink = excel().getCellData("NBL4","URL");
        String Etitle = excel().getCellData("NBL4","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[6]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[6]/ul/li[4]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case97() throws Exception {
        String Elink = excel().getCellData("NBL5","URL");
        String Etitle = excel().getCellData("NBL5","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[6]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[6]/ul[1]/li[5]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case98() throws Exception {
        String Elink = excel().getCellData("NBDN1","URL");
        String Etitle = excel().getCellData("NBDN1","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[7]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[7]/ul/li[1]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case99() throws Exception {
        String Elink = excel().getCellData("NBDN2","URL");
        String Etitle = excel().getCellData("NBDN2","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[7]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[7]/ul/li[2]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case100() throws Exception {
        String Elink = excel().getCellData("NBDN3","URL");
        String Etitle = excel().getCellData("NBDN3","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[7]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[7]/ul/li[3]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }

    @Test
    public void HP_case101() throws Exception {
        String Elink = excel().getCellData("NBDN4","URL");
        String Etitle = excel().getCellData("NBDN4","Title");

        WebElement GT = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[7]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(GT).click().build().perform();

        WebElement element = homePage.navBar.findElement(By.xpath("./div[1]/div[2]/ul[1]/li[7]/ul/li[4]"));
        homePage.navigate(Elink, Etitle, element );
        Thread.sleep(2000);
    }


    @Test
    public void HP_case102() throws Exception {  // Find the button element and click it to open tab 2
        Actions actions = new Actions(driver);
        homePage.searchInput.sendKeys(excel().getCellData("search", "URL"));
        actions.keyDown(Keys.CONTROL).moveToElement(homePage.searchInput).sendKeys(Keys.ENTER).perform();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String expectedTitle = excel().getCellData("search", "Title");
        String validPageTitle = driver.getTitle();

        driver.close();
        driver.switchTo().window(tabs.get(0));

        Assert.assertTrue(validPageTitle.contains(expectedTitle));
    }


    @Test
    public void HP_case103() throws Exception {
        Actions actions = new Actions(driver);
        homePage.searchInput.sendKeys(excel().getCellData("search", "URL"));
        actions.keyDown(Keys.CONTROL).moveToElement(homePage.searchBtn).click();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String expectedTitle = excel().getCellData("search", "Title");
        String validPageTitle = driver.getTitle();

        driver.close();
        driver.switchTo().window(tabs.get(0));

        Assert.assertTrue(validPageTitle.contains(expectedTitle));
    }

    @Test
    public void HP_case104() throws Exception {
        homePage.Block1.click();

        String expectedURL = excel().getCellData("Block1", "URL");
        String validURL = driver.getCurrentUrl();

        String expectedTitle = excel().getCellData("Block1", "Title");
        String validTitle = driver.getTitle();

        homePage.validPageTitle(expectedTitle);
        Assert.assertEquals(validURL, expectedURL);
    }

    @Test
    public void HP_case105() throws Exception {
        homePage.Block1.click();

        String expectedURL = excel().getCellData("Block2", "URL");
        String validURL = driver.getCurrentUrl();

        String expectedTitle = excel().getCellData("Block2", "Title");

        homePage.validPageTitle(expectedTitle);
        Assert.assertEquals(validURL, expectedURL);
    }

    @Test
    public void HP_case106() throws Exception {
        homePage.Block1.click();

        String expectedURL = excel().getCellData("Block3", "URL");
        String validURL = driver.getCurrentUrl();

        String expectedTitle = excel().getCellData("Block3", "Title");
        String validTitle = driver.getTitle();

        homePage.validPageTitle(expectedTitle);
        Assert.assertEquals(validURL, expectedURL);
    }

    @Test
    public void HP_case107() throws Exception {
        homePage.Block1.click();

        String expectedURL = excel().getCellData("Block4", "URL");
        String validURL = driver.getCurrentUrl();

        String expectedTitle = excel().getCellData("Block4", "Title");
        String validTitle = driver.getTitle();

        homePage.validPageTitle(expectedTitle);
        Assert.assertEquals(validURL, expectedURL);
    }

    @Test
    public void HP_case108() throws Exception {
        Actions actions = new Actions(driver);
        homePage.searchInput.sendKeys(excel().getCellData("AbSearch", "URL"));
        actions.keyDown(Keys.CONTROL).moveToElement(homePage.searchInput).sendKeys(Keys.ENTER).perform();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String expectedResult = excel().getCellData("AbSearch", "Title");
        String result_stats = driver.findElement(By.id("result-stats")).getText();

        driver.close();
        driver.switchTo().window(tabs.get(0));
        Assert.assertTrue(result_stats.contains(expectedResult));
        Thread.sleep(2000);

    }


}

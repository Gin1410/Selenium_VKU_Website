package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Base;

import java.util.List;

public class HomePage extends Base {

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "ntop")
    @CacheLookup
    public WebElement topBar;

    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/a")
    @CacheLookup
    public List<WebElement> topBarLeftItem;

    @FindBy(xpath = "//body/div[1]/div[1]/div[3]")
    @CacheLookup
    public WebElement topBarRight;

    @FindBy(xpath = "//body/div[1]/div[1]/div[3]/ul[1]/li/a/button")
    @CacheLookup
    public List<WebElement> topBarRightItem;

    @FindBy(xpath ="//body/div[2]/div[1]/div[1]/div[1]/a")
    @CacheLookup
    public WebElement logoImg;

    @FindBy(xpath = "//body/div[2]/div[1]/div[1]/div[2]/div[1]/a[1]")
    @CacheLookup
    public WebElement translateImg;

    @FindBy(className = "navbar")
    @CacheLookup
    public WebElement navBar;

    @FindBy(className = "dropdown")
    @CacheLookup
    public List<WebElement> navBarDropdown;

    @FindBy(className = "dropdown-menu")
    @CacheLookup
    public List<WebElement> navBarDropdownMenu;

    @FindBy(className = "lsearch")
    @CacheLookup
    public WebElement searchBox;

    @FindBy(className = "txt-search")
    @CacheLookup
    public WebElement searchInput;

    @FindBy(className = "btn-search")
    @CacheLookup
    public WebElement searchBtn;

    @FindBy(xpath = "//body/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]")
    @CacheLookup
    public WebElement sideBar;

    @FindBy(id = "block-views-tin-noi-bat-block")
    @CacheLookup
    public WebElement sideBarList;

    @FindBy(xpath = "//tbody/tr")
    @CacheLookup
    public List<WebElement> sideBarListItem;

    @FindBy(className = "shadow_footer")
    @CacheLookup
    public WebElement footer;

    @FindBy(xpath= "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]")
    @CacheLookup
    public WebElement Block1;




    public String converColor(String rgbaColor){
        Color color = Color.fromString(rgbaColor);
        int red = color.getColor().getRed();
        int green = color.getColor().getGreen();
        int blue = color.getColor().getBlue();
        String hexColor = String.format("#%02x%02x%02x", red, green, blue);
        return hexColor;
    }



}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Base;

public class SignInPage extends Base{

    @FindBy(id = "logIn")
    @CacheLookup
    public WebElement SignInBtn;

    @FindBy(id = "identifierId")
    @CacheLookup
    public WebElement emailInput;

    @FindBy(xpath = "//body/div[1]/div[1]/div[2]/div[1]/c-wiz[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]")
    @CacheLookup
    public WebElement nextBTN;

    @FindBy(name = "Passwd")
    @CacheLookup
    public WebElement passInput;

    @FindBy(className = "VfPpkd-Jh9lGc")
    @CacheLookup
    public WebElement nextBTN2;

    @FindBy(className = "VfPpkd-muHVFf-bMcfAe")
    @CacheLookup
    public WebElement checkBox;

    @FindBy(xpath = "//h2[contains(text(),'Quỳnh')]")
    @CacheLookup
    public WebElement userName;

    @FindBy(className = "o6cuMc")
    @CacheLookup
    public WebElement errorGmail;


    @FindBy(xpath ="//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/span[1]")
    @CacheLookup
    public WebElement errorPass1;

    @FindBy(xpath ="//span[contains(text(),'Wrong password. Try again or click Forgot password')]")
    @CacheLookup
    public WebElement errorPass2;

    @FindBy(xpath ="//span[contains(text(),'Enter a password')]")
    @CacheLookup
    public WebElement errorPass3;

    public SignInPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



}

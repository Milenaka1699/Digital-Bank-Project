package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);

    }
    @FindBy(id="username")
    private WebElement usernameTextBox;

    @FindBy(id="password")
    private  WebElement passwordTextBox;

    @FindBy(id="remember-me")
    private WebElement remember_meCheckBox;

    @FindBy(xpath="//button")
    private  WebElement submitBtn;

    @FindBy(xpath="//a[contains(text(), 'Sign Up Here')]")
    private WebElement signUpHereLink;

    //create Action Methods
    public  void login(String username, String password) {
        usernameTextBox.sendKeys(username);
        passwordTextBox.sendKeys(password);


    }
    public void rememberMeBtn() {
        remember_meCheckBox.click();
    }
    public void submitLogin() {
        submitBtn.click();
    }


}

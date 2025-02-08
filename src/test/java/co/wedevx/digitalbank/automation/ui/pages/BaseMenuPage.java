package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMenuPage  extends BasePage{
    public BaseMenuPage(WebDriver driver) {
        super(driver);
    }
    //User clicks on checking btn
    @FindBy(id = "checking-menu")
    protected WebElement checkingBtn;

    @FindBy (id = "new-checking-menu-item")  //user clicks on new checking button
    protected WebElement newCheckingBtn;

    @FindBy(id = "view-checking-menu-item")
    protected WebElement viewCheckingButton;

    @FindBy(id = "home-menu-item")
    protected WebElement homeButton;


    public void goToHomePage() {
        homeButton.click();
    }
}

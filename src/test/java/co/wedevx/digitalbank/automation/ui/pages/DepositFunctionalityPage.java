package co.wedevx.digitalbank.automation.ui.pages;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.temporal.WeekFields;

public class DepositFunctionalityPage extends BaseMenuPage {
    public DepositFunctionalityPage(WebDriver driver) {
        super(driver);
    }
    //Deposit Page ids and xpath here
    @FindBy(id = "deposit-menu-item")
    protected WebElement depositButton;
    @FindBy(id = "selectedAccount")
    protected WebElement accountTypeDropDown;

    @FindBy(id = "amount")
    protected  WebElement depositAmountTextBox;

    @FindBy(id = "submit")
    protected  WebElement submitDepositButton;
    public void clickOnDepositPage() {
        depositButton.click();
    }

    public void selectAccountType(String accountType) {
        Select select = new Select(accountTypeDropDown);

        // Check if the accountType is provided
        if (accountType == null || accountType.isEmpty()) {
            throw new IllegalArgumentException("Account type must not be empty.");
        }

        // Select account type from the dropdown
        select.selectByVisibleText(accountType);
    }
    public void enterDepositAmount(String amount) {
        if (amount == null) {
            amount = ""; // Leave the field empty to trigger validation
        }

        depositAmountTextBox.clear();
        depositAmountTextBox.sendKeys(amount);
        //submit button
        submitDepositButton.click();

    }
    }
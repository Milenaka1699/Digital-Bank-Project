package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.TransactionClass;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewCheckingSteps {
    WebDriver driver = Driver.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private CreateCheckingPage createCheckingPage = new CreateCheckingPage(driver);
    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);


    @Given("the user logged in as {string} and password {string}")
    public void the_user_logged_in_as_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @When("the user creates a new checking account with following data:")
    public void the_user_creates_a_new_checking_account_with_following_data(List<NewCheckingAccountInfo> checkingAccountInfoList) {
     createCheckingPage.createNewChecking(checkingAccountInfoList);
    }

    @Then("user should see the green {string} message")
    public void userShouldSeeTheGreenMessage(String expectedConfMessage) {
        expectedConfMessage  = "Confirmation " + expectedConfMessage + "\n×";
        assertEquals(expectedConfMessage, viewCheckingAccountPage.getActualCreateAccountConfirmationMessage());
    }

    @Then("the user should see newly added account card")
    public void the_user_should_see_newly_added_account_card(List<AccountCard> accountCardList) {
      Map<String, String> actualResultMap= viewCheckingAccountPage.getNewlyAddedCheckingAccountInfoMap();

        AccountCard expectedResult = accountCardList.get(0);

        //assertEquals(expectedResult.getAccountName(), actualResultMap.get("actualAccountName"));
        System.out.println("Expected Account Name: " + expectedResult.getAccountName());
        System.out.println("Actual Account Name: " + actualResultMap.get("actualAccountName"));

        assertEquals("Account: " + expectedResult.getAccountType(), actualResultMap.get("actualAccountType"));
        assertEquals("Ownership: " + expectedResult.getOwnership(), actualResultMap.get("actualOwnership"));
        //Assertions.assertEquals(expectedResult.getAccountNumber(), actualResultMap.get("actualAccountNumber"));
        assertEquals("Interest Rate: " + expectedResult.getInterestRate(), actualResultMap.get("actualInterestRate"));

        String expectedBalance = String.format("%.2f", expectedResult.getBalance());
        assertEquals("Balance: $" + expectedResult, actualResultMap.get("actualBalance"));


    }
    @Then("the user should see the following transactions")
    public void the_user_should_see_the_following_transactions(List<TransactionClass> expectedTransactions) {
    Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddeddTransactionInfoMap();
    TransactionClass expectedTransaction = expectedTransactions.get(0);
    assertEquals(expectedTransaction.getCategory(), actualResultMap.get("actualCategory"), "Transaction category mismatch");
    assertEquals(expectedTransaction.getAmount(), Double.parseDouble(actualResultMap.get("actualAmount")), "Transaction amount mismatch");
    assertEquals(expectedTransaction.getBalance(), Double.parseDouble(actualResultMap.get("actualBalance")), "Transaction balance mismatch");
    viewCheckingAccountPage.goToHomePage();
    createCheckingPage.goToHomePage();
    }
}

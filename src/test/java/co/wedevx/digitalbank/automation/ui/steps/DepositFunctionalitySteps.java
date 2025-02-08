package co.wedevx.digitalbank.automation.ui.steps;


import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.TransactionClass;
import co.wedevx.digitalbank.automation.ui.pages.*;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DepositFunctionalitySteps {
   LoginPage loginPage = new LoginPage(getDriver());
RegistrationPage registrationPage = new RegistrationPage(getDriver());
CreateCheckingPage createCheckingPage = new CreateCheckingPage(getDriver());
ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(getDriver());
DepositFunctionalityPage depositFunctionalityPage = new DepositFunctionalityPage(getDriver());

    // Utility method to generate random email and password


@Given("the user navigates to Digital Bank signup page")
    public void theUserNavigatesToDigitalBankSignupPage() {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.signinpageurl"));
        assertEquals("Digital Bank", getDriver().getTitle(), "Registration page Title mismatch");
    }
    @Given("user logins with email {string} and password {string}")
    public void userLoginsWithEmailAndPassword(String email, String password) {
    loginPage.login(email, password);
    loginPage.rememberMeBtn();
    loginPage.submitLogin();


    }

//    @Given("the user registers a new account as follows with mock email and ssn:")
//    public void the_user_registers_a_new_account_as_follows_with_mock_email_and_ssn(List<Map<String, String>> registrationTestDataListMap) {
//        registrationPage.fillOutRegistrationForm(registrationTestDataListMap);
//        // Generate random email and password
//        userEmail = generateRandomEmail();
//        userPassword = generateRandomPassword();
//
//        // Set the generated email and password into the registration data map
//        registrationTestDataListMap.get(0).put("email", userEmail);
//        registrationTestDataListMap.get(0).put("password", userPassword);
//        registrationPage.fillOutRegistrationForm(registrationTestDataListMap);
    //}
//    @And("user signs up with login and password")
//    public void userSignsUpWithLoginAndPassword() {
//        loginPage.login(userEmail, userPassword);
//        loginPage.submitLogin();
//    }
    @When("the user creates a new checking account with following info:")
    public void the_user_creates_a_new_checking_account_with_following_info(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        createCheckingPage.createNewChecking(checkingAccountInfoList);
    }
    @Then("user should get the green {string} message")
    public void userShouldGetTheGreenMessage(String expectedConfMessage) {
        expectedConfMessage  = "Confirmation " + expectedConfMessage + "\n×";
        assertEquals(expectedConfMessage, viewCheckingAccountPage.getActualCreateAccountConfirmationMessage());
    }

    @Then("user navigates to Deposit Page")
    public void user_navigates_to_deposit_page() {

    depositFunctionalityPage.clickOnDepositPage();
    }
    @Given("user  clicks on accounts Dropdown and selects {string} account")
    public void user_clicks_on_accounts_dropdown_and_selects_account(String accountType) {
        depositFunctionalityPage.selectAccountType(accountType);
    }

    @Then("the user makes a deposit of {string} and clicks on submit button")
    public void the_user_makes_a_deposit_of_and_clicks_on_submit_button(String depositAmount) {
        depositFunctionalityPage.enterDepositAmount(depositAmount);
    }
    @Then("the user should see the following transaction")
    public void the_user_should_see_the_following_transaction(List<TransactionClass> expectedTransactions) {
        Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddeddTransactionInfoMap();
        TransactionClass expectedTransaction = expectedTransactions.get(0);
        assertEquals(expectedTransaction.getCategory(), actualResultMap.get("actualCategory"), "Transaction category mismatch");
        assertEquals(expectedTransaction.getAmount(), Double.parseDouble(actualResultMap.get("actualAmount")), "Transaction amount mismatch");
        assertEquals(expectedTransaction.getBalance(), Double.parseDouble(actualResultMap.get("actualBalance")), "Transaction balance mismatch");
        viewCheckingAccountPage.goToHomePage();
        createCheckingPage.goToHomePage();
    }


    @Given("user selects {string} and and inputs deposit amount {string}")
    public void user_selects_and_and_inputs_deposit_amount(String string, String string2) {

    }
    @Then("the {string} field displays a {string} error message")
    public void the_field_displays_a_error_message(String string, String string2) {
viewCheckingAccountPage.goToHomePage();
depositFunctionalityPage.goToHomePage();
createCheckingPage.goToHomePage();
    }


}





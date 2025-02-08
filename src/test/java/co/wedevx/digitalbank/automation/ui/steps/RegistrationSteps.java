package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegistrationSteps {

    RegistrationPage registrationPage = new RegistrationPage(getDriver());
    List<Map<String, Object>> nextValueList = new ArrayList<>();


    @Given("user navigates to Digital Bank signup page")
    public void user_navigates_to_digital_bank_signup_page() {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpageurl"));
        assertEquals("milenaka1699.mydevx.com", getDriver().getTitle(), "Registration page Title mismatch");

    }
    @When("user creates account with following fields")
    public void user_creates_account_with_following_fields( List<Map<String, String>> registrationTestDataListMap) {
registrationPage.fillOutRegistrationForm(registrationTestDataListMap);

    }
    @Then("user should be displayed with the message {string}")
    public void user_should_be_displayed_with_the_message(String expectedSuccessMessage) {
assertEquals(expectedSuccessMessage, registrationPage.getMessage(), "Success message mismatch");
    }


    @Then("user should see the {string} required field error message {string}")
    public void userShouldSeeTheRequiredFieldErrorMessage(String fieldName, String expectedErrorMessage) {
        String actualErrorMessage = registrationPage.getRequiredFieldErrorMessage(fieldName);
assertEquals(expectedErrorMessage, actualErrorMessage, "the error message of required " + fieldName + " mismatch");
    }

    @Then("the following user info should be saved in the digital bank")
    public void theFollowingUserInfoShouldBeSavedInTheDigitalBank(List<Map<String, String>> expectedUserProfileInfoDBlist) {
        Map<String, String> expectedUserInfoMap =  expectedUserProfileInfoDBlist.get(0);
        String queryUserTable = String.format("select *from users where username='%s'", expectedUserInfoMap.get("email"));
        String queryForUserProfile = String.format("select *from user_profile where email_address='%s'", expectedUserInfoMap.get("email"));

        List<Map<String, Object>> actualUserInfoList = DBUtils.runSQLSelectQuery(queryUserTable);
        List<Map<String, Object>> actualUserProfileInfoList = DBUtils.runSQLSelectQuery(queryForUserProfile);
        assertEquals(1, actualUserInfoList.size(), "registration generated duplicate users");
        assertEquals(1, actualUserProfileInfoList.size(), "registration generated duplicate users");

        Map<String, Object> actualUserInfoMap = actualUserInfoList.get(0);
        Map<String, Object> actualUserProfileInfoMap = actualUserProfileInfoList.get(0);
        assertEquals(expectedUserInfoMap.get("title"), actualUserProfileInfoMap.get("title"), "Registration generated wrong title");
        assertEquals(expectedUserInfoMap.get("firstName"), actualUserProfileInfoMap.get("first_name"), "Registration generated wrong name");
        assertEquals(expectedUserInfoMap.get("lastName"), actualUserProfileInfoMap.get("last_name"), "Registration generated wrong last name");
        assertEquals(expectedUserInfoMap.get("gender"), actualUserProfileInfoMap.get("gender"), "Registration generated wrong gender");
       assertEquals(nextValueList.get(0).get("next_val"), actualUserInfoMap.get("id"));

        // assertEquals(expectedUserInfoMap.get("dob"), actualUserProfileInfoMap.get("dob"), "Registration generated wrong dob");
        assertEquals(expectedUserInfoMap.get("ssn"), actualUserProfileInfoMap.get("ssn"), "Registration generated wrong ssn");
        assertEquals(expectedUserInfoMap.get("email"), actualUserProfileInfoMap.get("email_address"), "Registration generated wrong email");
        assertEquals(expectedUserInfoMap.get("address"), actualUserProfileInfoMap.get("address"), "Registration generated wrong address");
        assertEquals(expectedUserInfoMap.get("locality"), actualUserProfileInfoMap.get("locality"), "Registration generated wrong locality");
        assertEquals(expectedUserInfoMap.get("region"), actualUserProfileInfoMap.get("region"), "Registration generated wrong region");
        assertEquals(expectedUserInfoMap.get("postalCode"), actualUserProfileInfoMap.get("postal_code"), "Registration generated wrong postal code");
        assertEquals(expectedUserInfoMap.get("country"), actualUserProfileInfoMap.get("country"), "Registration generated wrong country");
        assertEquals(expectedUserInfoMap.get("homePhone"), actualUserProfileInfoMap.get("home_phone"), "Registration generated wrong homePhone");
        assertEquals(expectedUserInfoMap.get("mobilePhone"), actualUserProfileInfoMap.get("mobile_phone"), "Registration generated wrong mobile phone");
        assertEquals(expectedUserInfoMap.get("workPhone"), actualUserProfileInfoMap.get("work_phone"), "Registration generated wrong work phone");
        //validate users table
        assertEquals(expectedUserInfoMap.get("accountNonExpired"), String.valueOf(actualUserInfoMap.get("account_non_expired")), "accountNonExpired Mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("accountNonLocked"), String.valueOf(actualUserInfoMap.get("account_non_locked")), "AccountNonLocked mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("credentialsNonExpired"), String.valueOf(actualUserInfoMap.get("credentials_non_expired")), "CredentialsNonExpired mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("enabled"), String.valueOf(actualUserInfoMap.get("enabled")), "Account enabled mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("email"), actualUserInfoMap.get("username"), "username mismatch upon registration");
        assertEquals(137, actualUserInfoMap.get("id"), "ID Mismatch");
       //how to validate functionalities with auto increments with Cucumber, Selenium, SQL
        long expectedUserProfileId =  Long.parseLong(String.valueOf(nextValueList.get(0).get("next_val")));
        assertEquals(++expectedUserProfileId, actualUserProfileInfoMap.get("id"), "id mismatch");


    }

    @Given("The following user with {string} is not in DB")
    public void theFollowingUserWithIsNotInDB(String email) {
        String queryForUserProfile = String.format("DELETE from user_profile where email_address='%s'", email);
        DBUtils.runSQLUpdateQuery(queryForUserProfile);

        String queryToGetNextValInHibernateSeqTable = String.format("select *from hibernate_sequence");
        nextValueList =DBUtils.runSQLSelectQuery(queryToGetNextValInHibernateSeqTable);


        String queryForUsers = String.format("DELETE from users where username='%s'", email);
        DBUtils.runSQLUpdateQuery(queryForUsers);
    }
}

package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.*;


public class Hooks {
    @Before("not @Registration")
    public void the_user_on_dbank_homepage() {
        Driver.getDriver().get("http://milenaka1699.mydevx.com/bank/login");

    }
    @Before("@Registration")
        public void clearTheDBForRegistration() {
        DBUtils.establishConnection();
    }
   @After("not @NegativeRegistrationCases")
    public void afterEachScenario(Scenario scenario) {
       try {
           Driver.takeScreenShot(scenario);
       } catch (Exception e) {
           System.err.println("Error taking screenshot: " + e.getMessage());
       } finally {
           Driver.closeDriver(); // Ensure WebDriver is closed after all actions
       }

    }
    @After()
    public static void closeConnectionToDB() {
        DBUtils.closeConnection();
    }
}

package co.wedevx.digitalbank.automation.ui.tests;

import co.wedevx.digitalbank.automation.ui.models.RandomUser;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import co.wedevx.digitalbank.automation.ui.utils.UserGenerator;
import io.cucumber.java.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegistrationTest {
    public static void main (String[] args) {
        // Singleton WebDriver instance
         WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         try {
                    //generate randomUser
                    RandomUser user = UserGenerator.generateRandomUser();
                    System.out.println("Generated User: " + user);

             //Navigate to the bankwebsite
             driver.get("https://dbank-qa.wedevx.co/bank/login");


                    //click the registration link
                    WebElement signUp = driver.findElement(By.xpath("//a[contains(text(), 'Sign Up Here')]"));
                    signUp.click();


             // Fill in the registration form
             WebElement titleDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
             titleDropdown.click();
             Select selectTitle = new Select(titleDropdown); // Create a Select object
             selectTitle.selectByVisibleText("Mr.");
//             selectTitle.selectByVisibleText(user.title); // Select title by visible text (e.g., "Mr.")



             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys(user.firstName);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName"))).sendKeys(user.lastName);

             // Select gender using radio buttons
             if (user.gender.equals("M")) {
                 WebElement maleRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='M']")));
                 maleRadioButton.click();
             } else if (user.gender.equals("F")) {
                 WebElement femaleRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='F']")));
                 femaleRadioButton.click();
             }

             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dob"))).sendKeys(user.dob);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ssn"))).sendKeys(user.ssn);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailAddress"))).sendKeys(user.email);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(user.password);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPassword"))).sendKeys(user.confirmPassword);

             // Click the "Next" button
             WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
             nextButton.click();

             // Fill in the address details
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address"))).sendKeys(user.address);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("locality"))).sendKeys(user.locality);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("region"))).sendKeys(user.region);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postalCode"))).sendKeys(user.postalCode);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country"))).sendKeys(user.country);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homePhone"))).sendKeys(user.homePhone);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobilePhone"))).sendKeys(user.mobilePhone);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("workPhone"))).sendKeys(user.workPhone);

                    WebElement termsRadioButton = driver.findElement(By.id("agree-terms"));
                    termsRadioButton.click();

             WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
             registerButton.click();

                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //close the browser
                    driver.quit();
                }

    }
}

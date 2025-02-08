package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.utils.MockData;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.it.Ma;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class RegistrationPage extends BasePage {


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    MockData mockData = new MockData();
    @FindBy(id = "title")
    private WebElement titleDropDown;

    @FindBy(id = "firstName")
    private WebElement firstNameBox;

    @FindBy(id = "lastName")
    private WebElement lastNameBox;

    @FindBy(xpath = "//input[@id='gender' and @value='M']")
    private WebElement maleRadioButton;

    @FindBy(xpath = "//input[@id='gender' and @value='F']")
    private WebElement femaleRadioButton;
    @FindBy(id = "dob")
    private WebElement dateOfBirthBox;

    @FindBy(id = "ssn")
    private WebElement securityNumberBox;

    @FindBy(id = "emailAddress")
    private WebElement emailAddressBox;

    @FindBy(id = "password")
    private WebElement passwordBox;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextButton;

    @FindBy(id = "address")
    private WebElement addressTextBox;

    @FindBy(id = "locality")
    private WebElement localityTextBox;

    @FindBy(id = "region")
    private WebElement regionTextBox;

    @FindBy(id = "postalCode")
    private WebElement postalCodeTextBox;

    @FindBy(id = "country")
    private WebElement countryTextBox;

    @FindBy(id = "homePhone")
    private WebElement homePhoneTextBox;

    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneBox;

    @FindBy(id = "workPhone")
    private WebElement workPhoneBox;



//    @FindBy(id = "termsAndPolicy")
//    private WebElement termsAndPolicyCheckbox;
    @FindBy(xpath = "//input[@type='checkbox']/..")
    private WebElement termsAndPolicyCheckbox;

    @FindBy(xpath = "//button[contains(text(), 'Register')]")
    private WebElement registerButton;

    @FindBy(xpath ="//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']")
    private WebElement messageLabel;




    public void fillOutRegistrationForm(List<Map<String, String>> registrationPageTestDataListOfMap) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(titleDropDown)); // Wait for dropdown visibility
            Select titleSelect = new Select(titleDropDown);

            Map<String, String> firstRow = registrationPageTestDataListOfMap.get(0);

            if (firstRow.get("title") != null) {
                System.out.println("Title from test data: " + firstRow.get("title"));
                titleSelect.selectByVisibleText(firstRow.get("title"));
            }

            if (firstRow.get("firstName") != null) {
                firstNameBox.sendKeys(firstRow.get("firstName"));
            }

            if (firstRow.get("lastName") != null) {
                lastNameBox.sendKeys(firstRow.get("lastName"));
            }

            // Select the correct gender radio button
            if (firstRow.get("gender") != null) {
                if (firstRow.get("gender").equalsIgnoreCase("M")) {
                    maleRadioButton.click();
                } else if (firstRow.get("gender").equalsIgnoreCase("F")) {
                    femaleRadioButton.click();
                }
            }
            if (firstRow.get("dob") != null) {
                dateOfBirthBox.sendKeys(firstRow.get("dob"));
            }

            if (firstRow.get("ssn") != null) {
                securityNumberBox.sendKeys(firstRow.get("ssn"));
//                if (firstRow.get("ssn").equalsIgnoreCase("random")) {
//                    securityNumberBox.sendKeys(mockData.generateRandomSsn());
                //}
            }
            if (firstRow.get("email") != null) {
                emailAddressBox.sendKeys(firstRow.get("email"));
//                if (firstRow.get("email").equalsIgnoreCase("random")) {
//
//                    Map<String, String> mockNameAndEmail = mockData.generateRandomNameAndEmail();
//                    emailAddressBox.sendKeys(mockNameAndEmail.get("email"));
//                }
            }

            if (firstRow.get("password") != null) {
                passwordBox.sendKeys(firstRow.get("password"));
                confirmPasswordBox.sendKeys(firstRow.get("confirmPassword"));
            }
            nextButton.click();
            if (addressTextBox.isDisplayed()) {

                if (firstRow.get("address") != null) {
                    addressTextBox.sendKeys(firstRow.get("address"));
                }
                if (firstRow.get("locality") != null) {
                    localityTextBox.sendKeys(firstRow.get("locality"));
                }
                if (firstRow.get("region") != null) {
                    regionTextBox.sendKeys(firstRow.get("region"));
                }
                if (firstRow.get("postalCode") != null) {
                    postalCodeTextBox.sendKeys(firstRow.get("postalCode"));
                }
                if (firstRow.get("country") != null) {
                    countryTextBox.sendKeys(firstRow.get("country"));
                }
                if (firstRow.get("homePhone") != null) {
                    homePhoneTextBox.sendKeys(firstRow.get("homePhone"));
                }
                if (firstRow.get("mobilePhone") != null) {
                    mobilePhoneBox.sendKeys(firstRow.get("mobilePhone"));
                }
                if (firstRow.get("workPhone") != null) {
                    workPhoneBox.sendKeys(firstRow.get("workPhone"));
                }
                //use it only for registration feature file
//                if (firstRow.get("termsCheckMark") != null) {
//                    if (firstRow.get("termsCheckMark").equalsIgnoreCase("true")) {
//                        termsAndPolicyCheckbox.click();
//                    }
            }
            termsAndPolicyCheckbox.click();
            registerButton.click();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error filling out the registration form: " + e.getMessage());
        }

        }

    public String getMessage() {
        return messageLabel.getText().substring(0, messageLabel.getText().lastIndexOf("."));

    }
    public  String getRequiredFieldErrorMessage(String fieldName) {
        switch (fieldName.toLowerCase()) {
            case "title":
                return titleDropDown.getAttribute("validationMessage");
            case "firstName":
                return firstNameBox.getAttribute("validationMessage");
            case "lastName":
                return lastNameBox.getAttribute("validationMessage");
            case "gender":
                return femaleRadioButton.getAttribute("validationMessage");
            case "dob":
                return dateOfBirthBox.getAttribute("validationMessage");
            case "ssn":
                return securityNumberBox.getAttribute("validationMessage");
            case "email":
                return emailAddressBox.getAttribute("validationMessage");
            case "password":
                return passwordBox.getAttribute("validationMessage");
            case "address":
                return addressTextBox.getAttribute("validationMessage");
            case "locality":
                return localityTextBox.getAttribute("validationMessage");
            case "region":
                return regionTextBox.getAttribute("validationMessage");
            case "postalCode":
                return postalCodeTextBox.getAttribute("validationMessage");
            case "country":
                return countryTextBox.getAttribute("validationMessage");
            case "homePhone":
                return homePhoneTextBox.getAttribute("validationMessage");
            case "mobilePhone":
                return mobilePhoneBox.getAttribute("validationMessage");
            case "workPhone":
                return workPhoneBox.getAttribute("validationMessage");
            case "termsCheckMark":
                return termsAndPolicyCheckbox.getAttribute("validationMessage");

        }

    return "Please fill out this field.";
    }
}


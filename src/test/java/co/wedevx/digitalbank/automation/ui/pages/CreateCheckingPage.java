package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage extends BaseMenuPage{

public CreateCheckingPage(WebDriver driver) {
   super(driver);
}


@FindBy(id = "Standard Checking")   //user selects account type
       private WebElement standardCheckingAccountTypeRadioBtn;


@FindBy (id = "Interest Checking")
       private WebElement interestCheckingAccountTypeRadioBtn;
    @FindBy (id = "Individual") //  //user selects ownership
    private WebElement individualOwnershipTypeRadioBtn;
@FindBy (id = "Joint") //  //user selects ownership
        private WebElement jointOwnershipTypeRadioBtn;

@FindBy (id = "name")
        private WebElement accountNameTxtBox;

@FindBy(id = "openingBalance") //    //user makes initial deposit
        private WebElement openingBalanceTextBox;

@FindBy (id = "newCheckingSubmit")     //user clicks on submit button
        private  WebElement submitBtn;

public void createNewChecking(List<NewCheckingAccountInfo> checkingAccountInfoList) {
    NewCheckingAccountInfo testDataForOneCheckingAcc = checkingAccountInfoList.get(0);

    checkingBtn.click();
    newCheckingBtn.click();

   assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"), getDriver().getCurrentUrl(), "new Checking button did not take to " + ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"));

   if (testDataForOneCheckingAcc.getCheckingAccountType().equalsIgnoreCase("Standard Checking")) {
       standardCheckingAccountTypeRadioBtn.click();
   } else if (testDataForOneCheckingAcc.getCheckingAccountType().equalsIgnoreCase("Interest Checking")) {
       interestCheckingAccountTypeRadioBtn.click();
   } else {
       throw new NoSuchElementException("Invalid checking account type option");
   }


   if (testDataForOneCheckingAcc.getAccountOwnership().equalsIgnoreCase("Individual")){
       individualOwnershipTypeRadioBtn.click();

   } else if (testDataForOneCheckingAcc.getAccountOwnership().equalsIgnoreCase("Joint")) {
jointOwnershipTypeRadioBtn.click();
   } else {
       throw new NoSuchElementException("Invalid ownership provided");
}


    accountNameTxtBox.sendKeys(testDataForOneCheckingAcc.getAccountName());
    //user makes initial deposit
    openingBalanceTextBox.sendKeys(String.valueOf(testDataForOneCheckingAcc.getInitialDepositAmount()));
    //user clicks on submit button
    submitBtn.click();
}

}

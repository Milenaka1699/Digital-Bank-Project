package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCheckingAccountPage extends BaseMenuPage{

    public ViewCheckingAccountPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "new-account-conf-alert")
    private WebElement newAccountConfAlertDiv;
@FindBy (xpath = "//div[@id='firstRow']/div")
private List<WebElement> allFirstRowDivs;

@FindBy (xpath = "//table[@id='transactionTable']/tbody/tr")
        private WebElement firstRowOfTransactions;
public Map<String, String> getNewlyAddeddTransactionInfoMap() {
    List<WebElement> firstRowColumns = firstRowOfTransactions.findElements(By.xpath("td"));

    String actualCategory = firstRowColumns.get (1). getText();
    String actualDescription = firstRowColumns.get (2) .getText ();
    double actualAmount = Double.parseDouble (firstRowColumns.get (3).getText().substring (1));
    double actualBalance = Double.parseDouble(firstRowColumns.get (4).getText().substring (1));

    Map<String, String> actualResultMap = new HashMap<>();
    actualResultMap.put("actualCategory", firstRowColumns.get(1).getText());
    actualResultMap.put("actualDescription", firstRowColumns.get(2).getText());
    actualResultMap.put("actualAmount", firstRowColumns.get(3).getText().substring(1));
    actualResultMap.put("actualBalance", firstRowColumns.get(4).getText().substring(1));
    return actualResultMap;
}


public Map<String, String> getNewlyAddedCheckingAccountInfoMap() {
    // Get the last account card element
    WebElement lastAccountCard = allFirstRowDivs.get(allFirstRowDivs.size() - 1);

    // Get the full text of the account card
    String fullText = lastAccountCard.getText();
    System.out.println("Account Card Text: " + fullText);

    // Initialize a map to store the extracted information
    Map<String, String> actualResultMap = new HashMap<>();

    // Extract information dynamically based on known labels
    try {
        actualResultMap.put("actualAccountName", fullText.substring(0, fullText.indexOf("\n")).trim());
        actualResultMap.put("actualAccountType", extractField(fullText, "Account:", "Ownership:"));
        actualResultMap.put("actualOwnership", extractField(fullText, "Ownership:", "Account number:"));
        actualResultMap.put("actualAccountNumber", extractField(fullText, "Account number:", "Interest Rate:"));
        actualResultMap.put("actualInterestRate", extractField(fullText, "Interest Rate:", "Balance:"));
        actualResultMap.put("actualBalance", extractField(fullText, "Balance:", null)); // Last field, no end keyword
    } catch (StringIndexOutOfBoundsException e) {
        System.err.println("Error while parsing account card: " + e.getMessage());
    }

    // Add a fallback for missing or unexpected data
    if (!actualResultMap.containsKey("actualAccountName") || actualResultMap.get("actualAccountName").isEmpty()) {
        actualResultMap.put("actualAccountName", "Default Account Name");
        System.err.println("Account name not found. Setting to default value.");
    }

    return actualResultMap;

}

    public String getActualCreateAccountConfirmationMessage () {
        return newAccountConfAlertDiv.getText();
    }
    private String extractField(String text, String startLabel, String endLabel) {
        int start = text.indexOf(startLabel);
        if (start == -1) {
            System.err.println("Start label not found: " + startLabel);
            return ""; // Return empty if start label is not found
        }
        start += startLabel.length();

        int end = (endLabel != null) ? text.indexOf(endLabel, start) : text.length();
        if (end == -1) {
            System.err.println("End label not found: " + endLabel + " (extracting until end)");
            return text.substring(start).trim(); // Extract until the end of text
        }

        return text.substring(start, end).trim();
    }


    // Overloaded helper to extract by indexes
    private String extractField(String text, int start, int end) {
        if (start < 0 || end > text.length() || start >= end) {
            return "";
        }
        return text.substring(start, end).trim();
    }
}

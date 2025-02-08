package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class BrowserHelper {
    //wait until the element is visible
    public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //wait until the element is clickable and click on it
    public static WebElement waitUntilElementClickableAndClick(WebDriver driver, WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();

        return clickableElement;

    }

    public static WebElement fluentWaitForElementPresence(WebDriver driver, WebElement element, int maxTimeToWait) {
        // Create a FluentWait instance
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(maxTimeToWait)) // Maximum wait time
                .pollingEvery(Duration.ofMillis(500))           // Polling interval
                .ignoring(NoSuchElementException.class);        // Ignore exceptions during polling

        // Wait for the element presence
        return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                if (element.isDisplayed()) {
                    return element;
                }
                return null;
            }
        });
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        try {
            // Check if the driver is a valid JavascriptExecutor instance
            if (driver instanceof JavascriptExecutor) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

                // Scroll the element into view
                jsExecutor.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
            } else {
                throw new UnsupportedOperationException("The WebDriver instance does not support JavaScript execution.");
            }
        } catch (Exception e) {
            // Handle exceptions gracefully and provide meaningful error messages
            throw new RuntimeException("Failed to scroll to the element: " + e.getMessage(), e);
        }
    }

    public static void clickElementWithText(WebDriver driver, String text) {
        try {
            // Locate the element using XPath with the given text
            WebElement element = driver.findElement(By.xpath("//*[text()='" + text + "']"));
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click the element with text: '" + text + "' due to: " + e.getMessage(), e);
//// Assuming you have a WebDriver instance `driver`
//String textToClick = "Click Me";
//BrowserHelper.clickElementWithText(driver, textToClick);
        }
    }
    // Fill text input field
    public static void fillTextInput(WebDriver driver, WebElement inputElement, String valueToFill) {
        try {
            // Scroll to the text input element to ensure it's in view
            scrollIntoView(driver, inputElement);

            // Check if the element is interactable (visible and enabled)
            if (inputElement.isDisplayed() && inputElement.isEnabled()) {
                // Clear any existing value in the input field
                inputElement.clear();
                // Fill the input field with the provided value
                inputElement.sendKeys(valueToFill);
            } else {
                throw new RuntimeException("The input element is either not visible or not enabled.");
            }
        } catch (Exception e) {
            // Handle exceptions gracefully and provide a meaningful error message
            throw new RuntimeException("Failed to fill the text input element due to: " + e.getMessage(), e);
        }
    }

}
//In this class we have to static methods that can be reused anywhere in the test
//waitForVisibilityOfElement();
//waitUntilElementClickableAndClickOnIt();

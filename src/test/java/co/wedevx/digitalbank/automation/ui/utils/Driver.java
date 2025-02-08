package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;

    private Driver() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getPropertiesValue("digitalbank.browser");
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "headless":
                    ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                    ChromeOptions options = new ChromeOptions();

                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("disable-extensions");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--proxy-server='direct://'");
                    options.addArguments("--proxy-bypass-list=*");
                    options.addArguments("--start-maximized");
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                    break;

            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
}
   public static void takeScreenShot(Scenario scenario) {
       if (driver == null) {
           System.err.println("WebDriver is null. Cannot take a screenshot.");
           return;
       }
       try {
           if (scenario.isFailed()) {
               // Taking screenshot
               final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
               // Adding the screenshot to the report
               scenario.attach(screenshot, "image/png", "screenshot");
           }
       } catch (WebDriverException e) {
           System.err.println("Error capturing screenshot: " + e.getMessage());
      // }
//        if (scenario.isFailed()) {
//            //taking screenshot
//            final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//            //adding the screenshot to the report
//            scenario.attach(screenshot, "image/png", "screenshot");

        }
   }
   public static void closeDriver() {
       if (driver != null) {
           try {
               driver.quit();
           } catch (WebDriverException e) {
               System.err.println("Error closing WebDriver: " + e.getMessage());
           } finally {
               driver = null; // Ensure the reference is cleared
           }
       }
   }
}

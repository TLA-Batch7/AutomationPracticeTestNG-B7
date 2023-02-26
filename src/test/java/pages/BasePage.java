package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import tests.BaseTest;
import utils.ConfigReader;

import static java.lang.Thread.sleep;

public class BasePage extends BaseTest {
    public BasePage(WebDriver driver){
        this.driver = driver;
    }
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 2; i++) {
            try {
                if (i % 2 == 0) {
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black;" +
                            "border: 3px solid red; background: yellow");
                    if (ConfigReader.readProperty("configuration.properties","takeScreenshots").equalsIgnoreCase("true"))
                        BaseTest.reportManager.logScreenshot();
                } else {
                    sleep(600);
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendKeys(WebElement element, String inputText){
        BaseTest.reportManager.logLocatorInfo("Entered text: " + inputText + " ", element);
        highlightElement(element);
        element.sendKeys(inputText);
    }

    public String getText(WebElement element){
        BaseTest.reportManager.logLocatorInfo("Retrieved TEXT: ", element);
        highlightElement(element);
        return element.getText();
    }
    public void click(WebElement element){
        BaseTest.reportManager.logLocatorInfo("Clicked on a BUTTON: ", element);
        highlightElement(element);
        element.click();
    }

    public void assertEquals(String actual, String expected){
        BaseTest.reportManager.logInfo("Expected: " + expected);
        BaseTest.reportManager.logInfo("Actual: " + actual);
        Assert.assertEquals(actual,expected);
    }
}


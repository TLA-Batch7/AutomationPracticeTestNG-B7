package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InputsPage extends BasePage {
    public InputsPage(WebDriver driver) { PageFactory.initElements(driver,this); }

    @FindBy(id="message")
    public WebElement messageField;

    @FindBy(name="button1")
    public WebElement showMessageBtn;

    @FindBy(name="message1")
    public WebElement messageResult;
}

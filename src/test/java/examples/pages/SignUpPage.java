package examples.pages;

import examples.data.*;

import io.appium.java_client.android.AndroidDriver;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class SignUpPage extends BasePage {
    @FindBy(id = "user_email")
    private WebElement emailField;

    @FindBy(id = "user_password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement submit;


    public static SignUpPage visit(AndroidDriver driver) {
        driver.get("http://a.testaddressbook.com/sign_up");
        return new SignUpPage(driver);
    }

    public SignUpPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signUp(UserData user) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        submit.click();

        waitWhileElementPresent(submit,
                "Form should have been submitted, but it appears not to have worked");
    }
}

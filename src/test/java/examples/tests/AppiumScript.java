package examples.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AppiumScript {
    private AppiumDriver driver;

    @Test
    public void signInAndroid() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "Nexus");

        File relative = new File("lib/AddressBook.apk");
        capabilities.setCapability("app", relative.getCanonicalPath());
        capabilities.setCapability("appWaitActivity", "com.address.book.MainActivity");
        capabilities.setCapability("appiumVersion", "1.7.2");

        driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("android.widget.Button"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("sign-in")));

        driver.findElement(MobileBy.AccessibilityId("Sign in")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("session_email"))).sendKeys(("user@example.com"));

        driver.findElement(By.id("session_password")).sendKeys("password");

        driver.findElement(By.xpath("//android.widget.Button[@content-desc='Sign in']")).click();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String status = result.isSuccess() ? "passed" : "failed";
        System.out.println(status);
        driver.quit();
    }

}
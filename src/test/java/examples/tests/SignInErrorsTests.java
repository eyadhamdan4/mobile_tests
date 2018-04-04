package examples.tests;

import examples.data.*;
import examples.pages.*;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.yaml.snakeyaml.Yaml;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class SignInErrorsTests {

    private static DesiredCapabilities createCapabilities(String value) throws FileNotFoundException {
        FileReader file = new FileReader("src/test/java/examples/config/platforms.yml");
        Map<String, Object> platforms = new Yaml().load(file);
        Map<String, Object> platform = (Map<String, Object>) platforms.get(value);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String key: platform.keySet()) {
            capabilities.setCapability(key, platform.get(key));
        }
        return capabilities;
    }

    @Test
    public void signInBlankPasswordUnsuccessful() throws MalformedURLException, FileNotFoundException {
        String platformProperty = System.getProperty("PLATFORM");
        String platform = (platformProperty != null) ? platformProperty : "androidChromeLocal";
        DesiredCapabilities capabilities = createCapabilities(platform);

        AndroidDriver driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        HomePage homePage = HomePage.visit(driver);
        SignInPage signInPage = homePage.navigateToSignIn();
        SignInPage signInPage2 = signInPage.signInUnsuccessfully(UserData.blankPassword());

        assertTrue(signInPage2.hasAlertNotice());
        assertTrue(HomePage.visit(driver).isNotSignedIn());

        driver.quit();
    }

}
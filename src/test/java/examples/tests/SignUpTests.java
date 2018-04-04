package examples.tests;

import examples.data.*;
import examples.pages.*;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class SignUpTests extends BaseTest {

    @Test()
    public void signInSuccessful() {
        SignUpPage signUpPage = SignUpPage.visit(driver);
        signUpPage.signUp(UserData.randomUser());

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isSignedIn());
    }

}
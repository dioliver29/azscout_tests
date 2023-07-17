package net.amzscout;

import net.amzscout.pages.components.SignPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignPageTests extends TestBase {

    SignPage signPage = new SignPage(driver);

    @Test
    @DisplayName("Sign Up with valid email")
    void signUpWithValidEmail() {
        signPage.fillEmailField(fakeEmail)
                .clickSubmitButton()
                .switchToDefaultContent()
                .waitForToastMessage();

        assertEquals(successLogin, signPage.getToastMessageTitle());
        assertEquals(successLoginText, signPage.getToastMessageText());
    }

    @Test
    @DisplayName("Sign Up with empty email")
    void signUpWithEmptyEmail() {
        signPage.clickSubmitButton();

        String emptyEmailText = driver.findElement(By.className("PgAuth-Sign-form"))
                .findElement(By.className("PgAuth-Error"))
                .getText();

        assertEquals(signUpEmptyEmailText, emptyEmailText);
    }

    @Test
    @DisplayName("Sign In with valid data")
    void signInWithValidData() {
        signPage.signUpTabClick()
                .fillEmailField("stikheeva.d@gmail.com")
                .fillPasswordField("NewPassword")
                .clickSubmitButton()
                .switchToDefaultContent()
                .waitForToastMessage();

        assertEquals(successLogin, signPage.getToastMessageTitle());
        assertEquals(successLoginText, signPage.getToastMessageText());
    }
}

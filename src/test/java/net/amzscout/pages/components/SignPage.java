package net.amzscout.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignPage {

    static WebDriver driver;

    public SignPage(WebDriver driver) {
        SignPage.driver = driver;
    }

    public SignPage switchToDefaultContent() {
        driver.switchTo().defaultContent();
        return this;
    }

    public SignPage waitForToastMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("custom-toaster__title")));
        return this;
    }

    public String getToastMessageTitle() {
        return driver.findElement(By.className("custom-toaster__title")).getText();
    }

    public String getToastMessageText() {
        return driver.findElement(By.className("custom-toaster__message")).getText();
    }


    public SignPage fillEmailField(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@type='text']"));
        emailField.sendKeys(email);
        return this;
    }

    public SignPage fillPasswordField(String password) {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys(password);
        return this;
    }

    public SignPage clickSubmitButton() {
        WebElement submitButton = driver.findElement(By.className("PgAuth-Sign-form__btn"));
        submitButton.click();
        return this;
    }

    public SignPage signUpTabClick() {
        WebElement signInTab = driver.findElement(By.cssSelector(".PgAuth-TabSection__button.clearBtn:nth-child(2)"));
        signInTab.click();
        return this;
    }
}

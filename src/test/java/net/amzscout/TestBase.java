package net.amzscout;

import com.github.javafaker.Faker;
import net.amzscout.pages.components.SignPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {

    SignPage signPage;
    static WebDriver driver;
    Faker faker = new Faker();
    static String baseUrl = "https://amzscout.net/app/#/database";
    String successLogin = "Login successful";
    String successLoginText = "You are successfully logged in";
    String signUpEmptyEmailText = "Please enter the correct email address";
    //генерация email
    String fakeEmail = faker.internet().emailAddress();

    @BeforeEach
    void beforeEach() {
        //перед каждым текстом открыть url и переключаться на содержимое в iframe
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        // Создать инстанс браузера для каждого теста
        driver = new ChromeDriver(options);

        // Сделать окно браузера на весь экран
        driver.manage().window().maximize();
        //открыть url
        driver.get(baseUrl);

        // Ожидаение появления frame и переключение на него
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".login__iframe")));
        driver.switchTo().frame(driver.findElement(By.className("login__iframe")));

        signPage = new SignPage(driver);
    }

    @AfterEach
    void tearDown() {
        //закрытие браузера после тестов
        driver.quit();
        driver = null;
    }

}

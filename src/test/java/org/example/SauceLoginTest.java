package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

class SauceLoginTest {

    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        // auto-download the matching chromedriver binary
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);          // disable save-password prompt
        prefs.put("profile.password_manager_enabled", false);     // disable password manager UI
        prefs.put("profile.password_manager_leak_detection", false); // disable breach bubble
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-infobars");               // optional
        options.addArguments("--headless=new");     // ← the key line
        options.addArguments("--window-size=1920,1080");   // (keeps screenshots full-size)

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test
    void loginShouldSucceed() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "URL should contain /inventory after successful login");
    }

    @AfterEach
    void teardown() {
//        if (driver != null) driver.quit();
    }
}

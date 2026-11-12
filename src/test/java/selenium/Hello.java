
package selenium;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Hello {

    WebDriver driver;

    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        File chrome = new File("/usr/bin/chromium");

        if (chrome.exists()) {
            options.setBinary(chrome.getAbsolutePath());
        }

        File driverFile = new File("/usr/bin/chromedriver");

        if (driverFile.exists()) {
            System.setProperty(
                "webdriver.chrome.driver",
                driverFile.getAbsolutePath()
            );
        }

        driver = new ChromeDriver(options);

        String page = new File("WebPages/Login.html")
                .getAbsoluteFile()
                .toURI()
                .toString();

        driver.get(page);
    }

    @Test
    public void validLoginTest() {

        driver.findElement(By.id("username"))
                .sendKeys("student");

        driver.findElement(By.id("password"))
                .sendKeys("12345");

        driver.findElement(By.tagName("button")).click();

        String message = driver.findElement(By.id("message"))
                .getText();

        assertEquals("Login Successful", message);

        System.out.println("Valid Login Test Passed");
    }

    @Test
    public void invalidLoginTest() {

        driver.findElement(By.id("username"))
                .sendKeys("wrong");

        driver.findElement(By.id("password"))
                .sendKeys("wrong123");

        driver.findElement(By.tagName("button")).click();

        String message = driver.findElement(By.id("message"))
                .getText();

        assertEquals(
            "Invalid Username or Password",
            message
        );

        System.out.println("Invalid Login Test Passed");
    }

    @AfterEach
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}

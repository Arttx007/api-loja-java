package com.arthur.api_loja.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://localhost:5500");
    }

    @Test
    void deveLogarComSucesso() {

        // Preencher campos
        WebElement email = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("email")));
                email.sendKeys("Dapperyoshigamer@gmail.com");

        WebElement senha = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("senha")));
                senha.sendKeys("123");

        // Clicar no botão (SEM ID)
        driver.findElement(By.cssSelector("#loginForm button"))
                .click();

        // Esperar mudança de página ou comportamento
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("dashboard"),
                ExpectedConditions.invisibilityOfElementLocated(By.id("loginForm"))
        ));

        // Validação (ajusta conforme seu sistema)
        Assertions.assertTrue(
                driver.getCurrentUrl().contains("dashboard")
        );
    }

    @AfterEach
    void fechar() {
        driver.quit();
    }
}
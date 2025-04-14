import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    public static final String URL_LEILOES = "http://localhost:8080/leiloes";
    public static final String WEB_DRIVER_KEY = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_VALUE = "src/drivers/chromedriver.exe";
    public static final String URL_DADOS_DO_LEILAO = "http://localhost:8080/leiloes/2";
    private WebDriver browser;

    @BeforeEach
    public void beforeEach() {
        System.setProperty(WEB_DRIVER_KEY, WEB_DRIVER_VALUE);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", new HashMap<String, Boolean>(){{
            put("credentials_enable_service", false);
            put("profile.password_manager_enabled", false);
        }});

        browser = new ChromeDriver();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        String username = "fulano";
        String password = "pass";

        browser.navigate().to(URL_LEILOES);
        browser.findElement(By.className("text-light")).click();
        browser.findElement(By.name("username")).sendKeys(username);
        browser.findElement(By.name("password")).sendKeys(password);
        browser.findElement(By.tagName("button")).click();

        assertEquals(URL_LEILOES, browser.getCurrentUrl());
        assertEquals(username, browser.findElement(By.className("font-italic")).getText());
        browser.quit();
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        String incorrectUsername = "fulanosjj";
        String incorrectPassword = "senha00288";

        browser.navigate().to(URL_LEILOES);
        browser.findElement(By.className("text-light")).click();
        browser.findElement(By.name("username")).sendKeys(incorrectUsername);
        browser.findElement(By.name("password")).sendKeys(incorrectPassword);
        browser.findElement(By.tagName("button")).click();

        assertEquals("http://localhost:8080/login?error", browser.getCurrentUrl());
        assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        assertThrows(NoSuchElementException.class,
                () -> browser.findElement(By.className("font-italic")));
        browser.quit();
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        browser.navigate().to(URL_DADOS_DO_LEILAO);

        assertNotEquals(URL_DADOS_DO_LEILAO, browser.getCurrentUrl());
        assertFalse(browser.getPageSource().contains("Dados do Leilão"));
        browser.quit();
    }

}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

public class LoginPage {

    private static final String URL_LEILOES = "http://localhost:8080/leiloes";
    private static final String URL_LOGIN_ERRO = "http://localhost:8080/login?error";
    private static final String URL_DADOS_DO_LEILAO = "http://localhost:8080/leiloes/2";
    private static final String WEB_DRIVER_KEY = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_VALUE = "src/drivers/chromedriver.exe";
    private WebDriver browser;

    public LoginPage() {
        System.setProperty(WEB_DRIVER_KEY, WEB_DRIVER_VALUE);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", new HashMap<String, Boolean>(){{
            put("credentials_enable_service", false);
            put("profile.password_manager_enabled", false);
        }});

        this.browser = new ChromeDriver(options);
    }

    public void navegaParaLeiloes() {
        this.browser.navigate().to(URL_LEILOES);
    }

    public void navegaParaDadosDoLeilao() {
        this.browser.navigate().to(URL_DADOS_DO_LEILAO);
    }

    public void clicaNoBotaoEntrar() {
        this.browser.findElement(By.className("text-light")).click();
    }

    public void preencheFormularioDeLogin(String username, String password) {
        this.browser.findElement(By.name("username")).sendKeys(username);
        this.browser.findElement(By.name("password")).sendKeys(password);
    }

    public void clicaNoBotaoDeLogin() {
        this.browser.findElement(By.tagName("button")).click();

    }

    public void esperaPelaUrlLeiloes() {
        new WebDriverWait(browser, Duration.ofSeconds(5).getSeconds())
                .until(ExpectedConditions.urlToBe(URL_LEILOES));
    }

    public void esperaPelaUrlLoginErro() {
        new WebDriverWait(browser, Duration.ofSeconds(5).getSeconds())
                .until(ExpectedConditions.urlToBe(URL_LOGIN_ERRO));
    }

    public void finalizaWebDriver() {
        this.browser.quit();
    }

    public boolean isUrlLeiloes() {
        return this.browser.getCurrentUrl().equals(URL_LEILOES);
    }

    public boolean isUrlLoginErro() {
        return this.browser.getCurrentUrl().equals(URL_LOGIN_ERRO);
    }

    public boolean isUrlDadosDoLeilao() {
        return this.browser.getCurrentUrl().equals(URL_DADOS_DO_LEILAO);
    }

    public boolean constainsLoginErroTexto() {
        return this.browser.getPageSource().contains("Usuário e senha inválidos.");
    }

    public boolean containsDadosDoLeilaoTexto() {
        return this.browser.getPageSource().contains("Dados do Leilão");
    }

    public String getTextoUsuarioLogadoLeiloes() {
        return this.browser.findElement(By.className("font-italic")).getText();
    }

    public WebElement getElementoUsuarioLogadoLeiloes() {
        return this.browser.findElement(By.className("font-italic"));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoginPage loginPage = (LoginPage) o;
        return Objects.equals(browser, loginPage.browser);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(browser);
    }
}

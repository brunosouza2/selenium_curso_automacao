package pages;

import driver.BrowserDriver;
import driver.BrowserEnumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class LoginPage {

    public static final String URL_LEILOES = "http://localhost:8080/leiloes";
    private static final String URL_LOGIN_ERRO = "http://localhost:8080/login?error";
    private static final String URL_DADOS_DO_LEILAO = "http://localhost:8080/leiloes/2";
    private WebDriver browser;

    public LoginPage(WebDriver browser) {
        this.browser = browser;
    }

    public LeiloesPage logar(String username, String password) {
        this.browser.navigate().to(URL_LEILOES);
        this.browser.findElement(By.className("text-light")).click();
        this.browser.findElement(By.name("username")).sendKeys(username);
        this.browser.findElement(By.name("password")).sendKeys(password);
        this.browser.findElement(By.tagName("button")).click();
        return new LeiloesPage(this.browser);
    }

    public void navegaParaDadosDoLeilao() {
        browser.navigate().to(URL_DADOS_DO_LEILAO);
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

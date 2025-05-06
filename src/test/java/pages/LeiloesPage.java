package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class LeiloesPage {

    private WebDriver browser;

    public LeiloesPage(WebDriver browser) {
        this.browser = browser;
    }

    public CadastroLeiloesPage carregarFormulario() {
        browser.findElement(By.id("novo_leilao_link")).click();
        return new CadastroLeiloesPage(this.browser);
    }

    public boolean verificaLeilaoCadastrado(String nomeLeilao, String valorLeilao) {
        new WebDriverWait(this.browser, 5).until(ExpectedConditions.urlToBe(LoginPage.URL_LEILOES));
        return  browser.getPageSource().contains(nomeLeilao) && browser.getPageSource().contains(valorLeilao);
    }

    public void fechar() {
        browser.quit();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LeiloesPage that = (LeiloesPage) o;
        return Objects.equals(browser, that.browser);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(browser);
    }

    @Override
    public String toString() {
        return "LeiloesPage{" +
                "browser=" + browser +
                '}';
    }
}

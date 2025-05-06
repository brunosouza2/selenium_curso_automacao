package tests;

import driver.BrowserDriver;
import driver.BrowserEnumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CadastroLeiloesPage;
import pages.LeiloesPage;
import pages.LoginPage;

import java.time.Duration;

public class LeiloesTest {

    private LeiloesPage leiloesPage;
    private CadastroLeiloesPage cadastroLeiloesPage;
    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        this.loginPage = new LoginPage(new BrowserDriver(BrowserEnumDriver.CHROME).getBrowser());

        this.leiloesPage = loginPage.logar("fulano", "pass");
        this.cadastroLeiloesPage = leiloesPage.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        leiloesPage.fechar();
    }

    @Test
    public void deveriaCadastrarNovoLeilao() {
        String nomeLeilao = "HB20";
        String valorInicial = "60000.00";

       this.leiloesPage = cadastroLeiloesPage.cadastrarNovoLeilao(nomeLeilao, valorInicial);

        Assertions.assertTrue(this.leiloesPage.verificaLeilaoCadastrado(nomeLeilao, valorInicial));
    }


}

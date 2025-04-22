package tests;

import driver.BrowserDriver;
import driver.BrowserEnumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CadastroLeiloesPage;
import pages.LeiloesPage;
import pages.LoginPage;

public class LeiloesTest {

    private LeiloesPage leiloesPage;
    private CadastroLeiloesPage cadastroLeiloesPage;

    @AfterEach
    public void afterEach() {
        leiloesPage.fechar();
    }

    @Test
    public void deveriaCadastrarNovoLeilao() {
        LoginPage loginPage = new LoginPage(new BrowserDriver(BrowserEnumDriver.CHROME).getBrowser());
        String nomeLeilao = "HB20";
        String valorInicial = "60000.00";

        this.leiloesPage = loginPage.logar("fulano", "pass");
        this.cadastroLeiloesPage = leiloesPage.carregarFormulario();
        this.cadastroLeiloesPage.cadastrarNovoLeilao(nomeLeilao, valorInicial);

        Assertions.assertTrue(this.leiloesPage.verificaLeilaoCadastrado(nomeLeilao, valorInicial));
    }
}

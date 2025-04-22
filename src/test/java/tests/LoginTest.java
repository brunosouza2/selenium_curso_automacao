package tests;

import driver.BrowserDriver;
import driver.BrowserEnumDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        this.loginPage = new LoginPage(new BrowserDriver(BrowserEnumDriver.CHROME).getBrowser());
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        String username = "fulano";
        String password = "pass";

        loginPage.logar(username, password);
        loginPage.esperaPelaUrlLeiloes();

        assertTrue(loginPage.isUrlLeiloes());
        assertEquals(username, loginPage.getTextoUsuarioLogadoLeiloes());

        loginPage.finalizaWebDriver();
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        String username = "fulanosjj";
        String password = "fulanosjj";

        loginPage.logar(username, password);
        loginPage.esperaPelaUrlLoginErro();

        assertTrue(loginPage.isUrlLoginErro());
        assertTrue(loginPage.constainsLoginErroTexto());
        assertThrows(NoSuchElementException.class,
                () -> loginPage.getElementoUsuarioLogadoLeiloes());

        loginPage.finalizaWebDriver();
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        loginPage.navegaParaDadosDoLeilao();

        assertFalse(loginPage.isUrlDadosDoLeilao());
        assertFalse(loginPage.containsDadosDoLeilaoTexto());

        loginPage.finalizaWebDriver();
    }

}

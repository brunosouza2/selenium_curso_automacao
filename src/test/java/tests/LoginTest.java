package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        this.loginPage = new LoginPage();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        String username = "fulano";

        loginPage.navegaParaLeiloes();
        loginPage.clicaNoBotaoEntrar();
        loginPage.preencheFormularioDeLogin(username, "pass");
        loginPage.clicaNoBotaoDeLogin();
        loginPage.esperaPelaUrlLeiloes();

        assertTrue(loginPage.isUrlLeiloes());
        assertEquals(username, loginPage.getTextoUsuarioLogadoLeiloes());

        loginPage.finalizaWebDriver();
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        loginPage.navegaParaLeiloes();
        loginPage.clicaNoBotaoEntrar();
        loginPage.preencheFormularioDeLogin("fulanosjj", "fulanosjj");
        loginPage.clicaNoBotaoDeLogin();
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

    package pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;

    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;

    public class CadastroLeiloesPage {

        private WebDriver browser;

        public CadastroLeiloesPage(WebDriver browser) {
            this.browser = browser;
        }

        public LeiloesPage cadastrarNovoLeilao(String nomeLeilao, String valorInicial) {
            String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            browser.findElement(By.id("nome")).sendKeys(nomeLeilao);
            browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
            browser.findElement(By.id("dataAbertura")).sendKeys(dataHoje);
            browser.findElement(By.id("button-submit")).click();
            return new LeiloesPage(this.browser);
        }


    }

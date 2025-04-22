package driver;

import exceptions.BrowserInvalidoException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

import static driver.BrowserEnumDriver.CHROME;

public class BrowserDriver {

    private WebDriver browser;
    private static final String CHROME_WEB_DRIVER_KEY = "webdriver.chrome.driver";
    private static final String CHROME_WEB_DRIVER_VALUE = "src/drivers/chromedriver.exe";

    public BrowserDriver(BrowserEnumDriver browserEnumDriver) {
        this.browser = selectDriver(browserEnumDriver);
    }

    public WebDriver getBrowser() {
        return browser;
    }

    private WebDriver selectDriver(BrowserEnumDriver browserEnumDriver) {
        if (browserEnumDriver.equals(CHROME)) {
            System.setProperty(CHROME_WEB_DRIVER_KEY, CHROME_WEB_DRIVER_VALUE);
            return new ChromeDriver(setDefaultChromeOptions());
        }
        throw new BrowserInvalidoException("Browser Invalido");
    }

    private ChromeOptions setDefaultChromeOptions() {
        return new ChromeOptions().setExperimentalOption("prefs", new HashMap<String, Boolean>(){{
            put("credentials_enable_service", false);
            put("profile.password_manager_enabled", false);
        }});
    }



}

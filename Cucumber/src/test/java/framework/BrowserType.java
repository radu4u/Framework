package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class BrowserType {
    public static WebDriver createInstance(String browserName) {
        WebDriver driver;
        browserName = (browserName != null) ? browserName : "firefox";

        switch (Browser.valueOf(browserName.toUpperCase())) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case HTMLUNIT:
                driver = new HtmlUnitDriver();
                break;
            case HTMLUNITJS:
                driver = new HtmlUnitDriver(true);
                break;
            default:
                driver = new FirefoxDriver();
                break;
        }
        return driver;
    }

    private static enum Browser {
        FIREFOX,
        CHROME,
        IE,
        HTMLUNIT,
        HTMLUNITJS;
    }
}

package framework;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.events.EventFiringWebDriver;
//
//import com.google.inject.Singleton;
//
//import cucumber.api.java.After;
//@Singleton
//public class EventBrowser extends EventFiringWebDriver{
//	private WebDriver driver ; 
//
//	public EventBrowser() {
//		super(new FirefoxDriver());
//	}
//	
//    private WebDriver getRealDriver() {
//        //if (driver == null) {
//        	driver = new FirefoxDriver();
//        //}
//        return driver;
//    }
//	
//	@After
//	public void close() {
//		super.close();
//		super.quit();
//	}
//	
//
//
//}

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.google.inject.Singleton;

import java.util.concurrent.TimeUnit;

@Singleton
public class EventBrowser extends EventFiringWebDriver {
    private static WebDriver REAL_DRIVER;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };

    static {
        REAL_DRIVER = BrowserType.createInstance(System.getProperty("browser"));
        REAL_DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public EventBrowser() {
        super(REAL_DRIVER);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    @Before
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        scenario.write("Current Page URL is " + getCurrentUrl());
        try {
            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

}

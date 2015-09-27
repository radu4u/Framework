package framework;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;

public class SeleniumCommands {

	private final WebDriver driver;
	@Inject
	public SeleniumCommands(EventBrowser driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}

	public boolean waitForTitleToBeDisplayed(String title, int time) {
		return new WebDriverWait(driver, time).until(
				ExpectedConditions.titleContains(title));
	}

	public WebElement waitForElementToBeDisplayed(WebElement element, int time) {
		return new WebDriverWait(driver, time).until(
				ExpectedConditions.visibilityOf(element));
	}

	public byte[] getScreenShot() {
		byte[] screenshot = null;
		try {
            screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
		return screenshot;
	}

    public void acceptPopUp() {
    	Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void doubleClick(WebElement element) {
    	new Actions(driver).doubleClick(element).perform();
    }

    public String executeJavaScript(String javaScript) {
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	return js.executeScript(javaScript).toString();
    }

    public void openNewWindowAndSwitchTo(int windowNumber) {
    	executeJavaScript("window.open()");
    	switchWindow(windowNumber);
    }

     public boolean switchWindow(int window) {
    	try {
    		driver.switchTo().window(getWindowHandles()[window]);
    		return true;
    	} catch (Exception e) {
			e.printStackTrace();
    		return false;
		}
    }

     protected String[] getWindowHandles() {
     	String windows = driver.getWindowHandles().toString()
     			.replace("[", "").replace("]", "").replace(" ", "");
     	return windows.split(",");
     }

}

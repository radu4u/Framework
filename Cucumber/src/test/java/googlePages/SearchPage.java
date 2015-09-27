package googlePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.google.inject.Inject;

import framework.EventBrowser;

public class SearchPage extends LoadableComponent<SearchPage> {

	private static final String URL = "https://www.google.co.uk";
	private final WebDriver driver;

	@Inject SearchResults sr;
	@FindBy (css = ".gsfi") private WebElement searchField;
	@FindBy (name = "btnG") private WebElement searchButton;

	@Inject
	public SearchPage(EventBrowser driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		driver.get(URL);
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(driver.getCurrentUrl().contains(URL),
				"Wrong page was displayed: " + driver.getCurrentUrl());
	}

	public void typeSearchQuery(String searchValue) {
		searchField.clear();
		searchField.sendKeys(searchValue);
	}

	public void clickSearchButton() {
		searchButton.click();
		sr.get();
	}

}

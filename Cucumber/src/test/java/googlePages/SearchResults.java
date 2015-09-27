package googlePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.google.inject.Inject;

import framework.EventBrowser;
import framework.SeleniumCommands;

public class SearchResults extends LoadableComponent<SearchResults> {

	private final WebDriver driver;
	@FindBy (css = "div[class*='hdtb-msel']") private WebElement webTabSelected;
	@FindBy (css = "#rso .srg div.g:nth-child(1) cite") private WebElement firstLink;
	@Inject SeleniumCommands sc;

	@Inject
	public SearchResults(EventBrowser driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		System.out.println("LOAD from Search Result");
		sc.waitForTitleToBeDisplayed("Google Search", 5);
	}

	@Override
	protected void isLoaded() throws Error {
		System.out.println("IS LOADED from Search Result");
		Assert.assertTrue(driver.getTitle().contains("Google Search"),
				"Title not displayed: " + driver.getTitle());
		System.out.println("Title from search resu is: " + driver.getTitle());

	}

	public String getFirstLink() {
		sc.waitForElementToBeDisplayed(firstLink, 10);
		return firstLink.getText();
	}

}

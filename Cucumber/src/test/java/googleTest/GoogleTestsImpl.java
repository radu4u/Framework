package googleTest;

import framework.EventBrowser;
import framework.SeleniumCommands;
import googlePages.SearchPage;
import googlePages.SearchResults;

import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Guice
public class GoogleTestsImpl {

	@Inject SearchPage sp;
	@Inject SearchResults sr;
	@Inject EventBrowser eb;
	@Inject SeleniumCommands sc;

	@Before
	public void beforeScenario() {
		eb.manage().deleteAllCookies();
	}

	@Given("^I open Google page$")
	public void i_open_Google_page() throws Throwable {
		sp.get();
	}

	@When("^I type (.*) on search box$")
	public void i_type_query_on_search_box(String query) throws Throwable {
		sp.typeSearchQuery(query);
		sp.clickSearchButton();
	}

	@Then("^the first result of search contains (.*)$")
	public void the_first_result_of_search_contains_code_google_com(String link) throws Throwable {
		Assert.assertTrue(sr.getFirstLink().contains(link),
	    		"First link is: " + sr.getFirstLink());
	}

	@After
	public void finish(Scenario scenario) {
		scenario.embed(sc.getScreenShot(), "image/png");
	}

	@Test
	public void test() {
		sp.get();
		sp.typeSearchQuery("page object");
		sp.clickSearchButton();
		System.out.println("First link is: " + sr.getFirstLink());
		Assert.assertTrue(sr.getFirstLink().contains("code.google.com"),
	    		"First link is: " + sr.getFirstLink());
	}

}

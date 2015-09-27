package googleTest;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(format =
	{"pretty", "html:target/surefire-reports/cucumber",
		"json:target/cucumber.json"},tags={"@GoogleSearch"},
				features="src/test/resources")
public class RunCukesTest extends AbstractTestNGCucumberTests {

}
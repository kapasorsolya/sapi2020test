package ro.sapientia.mesteri2015.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SCRUMAssignUserStepDefinition {

	protected WebDriver driver;
	
	@Before
	public void setup() {
		driver = new FirefoxDriver();
	}

	@Given("^I open the scrum tool add story page$")
	public void I_open_the_scrum_tool_add_story_page() throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/");
		
		WebElement addStoryButton = driver.findElement(By.id("add-button"));
		addStoryButton.click();
	}

	@When("^I enter \"([^\"]*)\" in the title textbox, select \"([^\"]*)\" and I push the add button$")
	public void I_enter_title_in_the_title_textbox_select_user_and_I_push_the_add_button(
			String title, String user) throws Throwable {
		WebElement titleTextBox = driver.findElement(By.id("story-title"));
		titleTextBox.clear();
		titleTextBox.sendKeys(title);
		
		Select userComboBox = new Select(driver.findElement(By.id("story-user")));
		userComboBox.selectByVisibleText(user);

		WebElement addStoryButton = driver.findElement(By.id("add-story-button"));
		addStoryButton.click();
	}

	@Then("^I should get result \"([^\"]*)\" on story detail page$")
	public void I_should_get_previously_selected_user_on_story_detail_page(String expectedUsername)
			throws Throwable {
		WebElement assignedTo = driver.findElement(By.id("assignedTo"));
		String actualAssignedTo = assignedTo.getText();
		
		Assert.assertEquals(expectedUsername, actualAssignedTo);

		driver.close();
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}
	
}

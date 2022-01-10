package com.revature.features;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApprovalStepImpl {
public static WebDriver driverApp;
	
	@BeforeAll
	public static void setupDriver() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driverApp = new ChromeDriver();
	}
	@Given("the user is on the approval page")
	public void the_user_is_on_the_approval_page() {
	   driverApp.get("C:\\Users\\chris\\Documents\\Revature-Training\\p1-trms-Christian-Seefeldt\\trms-front\\Approval.html");
	}
	
	@Given("the user is logged in as an approver")
	public void the_user_is_logged_in_as_an_approver() {
	    WebElement loginLink = driverApp.findElement(By.id("login"));
		loginLink.click();
		WebElement usernameInput = driverApp.findElement(By.id("username"));
		WebElement passwordInput = driverApp.findElement(By.id("password"));
		usernameInput.sendKeys("SManoson");
		passwordInput.sendKeys("p3");
		WebElement loginBtn = driverApp.findElement(By.id("loginBtn"));
		loginBtn.click();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driverApp)
				.withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(50));
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("loginForm"), 1));
	}
	@When("the user inputs {string} to search by requestor id")
	public void the_user_inputs_to_search_by_requestor_id(String string) {
		WebElement IdInput = driverApp.findElement(By.id("empid"));
		IdInput.sendKeys("2");
	}

	@When("clicks the view all requests button")
	public void clicks_the_view_all_requests_button() {
		WebElement reqBtn = driverApp.findElement(By.id("submit2"));
		reqBtn.click();
	}
	
	@SuppressWarnings("deprecation")

	@Then("the table has pending requests in it")
	public void the_table_has_pending_requests_in_it() {
		driverApp.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		List<WebElement> tableData = driverApp.findElements(By.tagName("td"));
	    assertFalse(tableData.isEmpty());

	}
	
	@AfterAll
	public static void closeDriver() {
		driverApp.quit();
	}
}

package com.revature.features;

import static org.junit.jupiter.api.Assertions.*;

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

public class ViewRequestsStepImpl {
public static WebDriver driverReq;
	
	@BeforeAll
	public static void setupDriver() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driverReq = new ChromeDriver();
	}

	@Given("the user is on the view requests page")
	public void the_user_is_on_the_view_requests_page() {
	   driverReq.get("C:\\Users\\chris\\Documents\\Revature-Training\\p1-trms-Christian-Seefeldt\\trms-front\\ViewRequest.html");
	}

	@Given("the user is logged in")
	public void the_user_is_logged_in() {
	    WebElement loginLink = driverReq.findElement(By.id("login"));
		loginLink.click();
		WebElement usernameInput = driverReq.findElement(By.id("username"));
		WebElement passwordInput = driverReq.findElement(By.id("password"));
		usernameInput.sendKeys("BSmith");
		passwordInput.sendKeys("p5");
		WebElement loginBtn = driverReq.findElement(By.id("loginBtn"));
		loginBtn.click();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driverReq)
				.withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(50));
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("loginForm"), 1));
	}


	@When("the user inputs {string} to search by employee id")
	public void the_user_inputs_to_search_by_employee_id(String string) {
		WebElement IdInput = driverReq.findElement(By.id("dataInput"));
		IdInput.sendKeys("2");
	}

	@When("clicks the search button")
	public void clicks_the_search_button() {
		WebElement reqBtn = driverReq.findElement(By.id("reqbutton"));
		reqBtn.click();
	}

	@SuppressWarnings("deprecation")
	@Then("the table has requests in it")
	public void the_table_has_requests_in_it() {
		driverReq.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		List<WebElement> tableData = driverReq.findElements(By.tagName("td"));
	    assertFalse(tableData.isEmpty());

	}
	@AfterAll
	public static void closeDriver() {
		driverReq.quit();
	}

}

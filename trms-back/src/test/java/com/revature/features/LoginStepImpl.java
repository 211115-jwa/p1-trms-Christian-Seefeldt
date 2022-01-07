package com.revature.features;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepImpl {
	public WebDriver driver;
	
	{
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driver = new ChromeDriver();
	}

	
	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
	    driver.get("C:\\Users\\chris\\Documents\\Revature-Training\\p1-trms-Christian-Seefeldt\\trms-front\\Login.html");
	}

	@When("the user enters {string} and {string} to log in")
	public void the_user_enters_and_to_log_in(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user clicks the login button")
	public void the_user_clicks_the_login_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the page says Welcome, {string}!")
	public void the_page_says_welcome(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the page says Incorrect Credentials")
	public void the_page_says_incorrect_credentials() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}

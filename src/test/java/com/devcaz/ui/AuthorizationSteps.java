package com.devcaz.ui;

import com.devcaz.PropertiesHolder;
import com.devcaz.api.infra.RestAssuredSpecificationHolder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.devcaz.ui.ElementClassName.ADMIN_MENU;
import static com.devcaz.ui.ElementId.USER_LOGIN_NAME;
import static com.devcaz.ui.ElementId.USER_PASSWORD;
import static com.devcaz.ui.ElementName.SIGN_IN_ICON;
import static com.devcaz.ui.ElementXPath.LOGOUT_ICON;
import static com.devcaz.ui.ElementXPath.USER_LOGOUT_ICON;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class AuthorizationSteps {
    private WebDriver driver;
    private String loginUrl;

    @Before
    public void setUp() {
        PropertiesHolder propertiesHolder = new PropertiesHolder();
        loginUrl = propertiesHolder.getProperty("loginUrl");
        driver = WebDriverCreator.getWebDriver();
    }

    @Given("User open auth page")
    public void userOpenAuthPage() {
        driver.get(loginUrl);
    }

    @When("User enters login {string}")
    public void He_enters_login(String login) {
        driver.findElement(By.id(USER_LOGIN_NAME.getElementId())).sendKeys(login);
    }

    @And("User enters password {string}")
    public void He_enters_password(String password) {
        driver.findElement(By.id(USER_PASSWORD.getElementId())).sendKeys(password);
    }

    @And("User submits request for auth")
    public void He_submits_request_for_auth() {
        driver.findElement(By.name(SIGN_IN_ICON.getElementName())).click();
    }

    @And("Assert loading admin menu")
    public void Assert_loading_admin_menu() {
        boolean isAdminMenu = driver.findElements(By.className(ADMIN_MENU.getElementClassName())).isEmpty();
        assertFalse(isAdminMenu);
    }

    @Then("Logout")
    public void Logout() {
        driver.findElement(By.xpath(USER_LOGOUT_ICON.getElementsXPath())).click();
        driver.findElement(By.xpath(LOGOUT_ICON.getElementsXPath())).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

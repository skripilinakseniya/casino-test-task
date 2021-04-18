package com.devcaz.ui;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.devcaz.ui.ElementXPath.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerTableLoadingSteps {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverCreator.getWebDriver();
    }

    @And("Submit request for open players menu")
    public void Submit_request_for_open_players_menu() {
        driver.findElement(By.xpath(PLAYERS_MENU_ICON.getElementsXPath())).click();
    }

    @And("Assert loading players table")
    public void Assert_loading_players_table() {
        boolean isPlayerTable = driver.findElements(By.xpath(PLAYERS_TABLE.getElementsXPath())).isEmpty();
        assertFalse(isPlayerTable);

        boolean playerTableElement = driver.findElements(By.xpath(PLAYER_TABLE_ELEMENT.getElementsXPath())).isEmpty();
        assertFalse(playerTableElement);
    }
}

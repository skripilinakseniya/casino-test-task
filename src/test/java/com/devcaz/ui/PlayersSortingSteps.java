package com.devcaz.ui;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.devcaz.ui.ElementXPath.*;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class PlayersSortingSteps {
    private WebDriver driver;
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    @Before
    public void setUp() {
        driver = WebDriverCreator.getWebDriver();
    }

    @And("Submit sort by date")
    public void Submit_sort_by_date() {
        driver.findElement(By.xpath(SORTING_ICON.getElementsXPath())).click();
    }

    @And("Assert sorted")
    public void Assert_sorted() {
        String dateFirstUserFromTable = driver.findElement(By.xpath(FIRST_PLAYER_REGISTRATION_DATE.getElementsXPath())).getText();
        String dateSecondUserFromTable = driver.findElement(By.xpath(SECOND_PLAYER_REGISTRATION_DATE.getElementsXPath())).getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        LocalDateTime dateFirstUser = LocalDateTime.parse(dateFirstUserFromTable, formatter);
        LocalDateTime dateSecondUser = LocalDateTime.parse(dateSecondUserFromTable, formatter);

        dateSecondUser.isAfter(dateFirstUser);

        assertFalse(dateSecondUser.isAfter(dateFirstUser));
    }
}
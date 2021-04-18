package com.devcaz.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverCreator {
    private static WebDriver driver;
    private static final String PATH = "C:\\Users\\kisen\\chromedriver.exe";

    public static boolean hasQuit(WebDriver driver) {
        return ((RemoteWebDriver) driver).getSessionId() == null;
    }

    public static WebDriver getWebDriver() {

        if (driver == null || hasQuit(driver)) {
            System.setProperty("webdriver.chrome.driver", PATH);
            driver = new ChromeDriver();
        }

        return driver;
    }
}

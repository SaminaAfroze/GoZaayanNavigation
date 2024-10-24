package com.Assesment.base;

import com.Assesment.constants.GLOBAL;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.Assesment.base.DriverOptions.*;

public class AutomationFactory {

    private WebDriver webDriver;
    private DriverOptions selectedDriverOption;
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch").toUpperCase();
    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");
    private final boolean headlessMode = Boolean.getBoolean("headless");

    public AutomationFactory() {
        DriverOptions driverOption = firefox; // Default to Firefox
        String browser = System.getProperty("browserType", driverOption.toString());

        try {
            driverOption = valueOf(browser);
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown driver specified, defaulting to '" + driverOption + "'....");
        } catch (NullPointerException e) {
            System.err.println("No driver specified, defaulting to '" + driverOption + "'....");
        }

        selectedDriverOption = driverOption;
    }

    public WebDriver getDriver() {
        if (webDriver == null) {
            instantiateWebDriver(selectedDriverOption);
        }
        return webDriver;
    }

    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    private void instantiateWebDriver(DriverOptions driverOption) {
        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Application Environment: " + GLOBAL.APPLICATION_ENVIRONMENT);
        System.out.println("Browser Selected: " + selectedDriverOption);
        System.out.println("Headless Mode: " + headlessMode);
        System.out.println("Connected to Selenium Grid: " + useRemoteWebDriver);
        System.out.println(" ");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        // Set headless mode capability if applicable
        if (headlessMode) {
            desiredCapabilities.setCapability("headless", true);
        }

        try {
            if (useRemoteWebDriver) {
                String host = System.getProperty("gridHost");
                desiredCapabilities.setBrowserName(driverOption.toString());

                // Handle specific browser adjustments
                if (driverOption.equals(DriverOptions.ie) && webDriver != null) {
                    webDriver.findElement(By.tagName("html")).sendKeys(Keys.CONTROL, "0");
                }

                webDriver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), desiredCapabilities);
            } else {
                webDriver = driverOption.getWebDriverObject(desiredCapabilities);
            }
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL for Remote WebDriver: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error initializing WebDriver: " + e.getMessage());
        }

        Dimension dimension = new Dimension(1920, 1080);
        webDriver.manage().window().setSize(dimension);
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        System.out.println("Cookies deleted");

        System.out.println("Dimensions of viewport is " + webDriver.manage().window().getSize());
        System.out.println("Leaving getWebDriver and driver is currently null? " + (webDriver == null));
    }

    public void setDriver(WebDriver webDriver) {
        this.webDriver = webDriver; // Allow setting the WebDriver instance externally
    }
}

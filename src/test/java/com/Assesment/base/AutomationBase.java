package com.Assesment.base;

import com.Assesment.utils.ConfigReader;
import com.Assesment.utils.context.TestContext;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutomationBase {
    private static List<AutomationFactory> automationThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<AutomationFactory> driverThread;
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;
    private static String strApiGateway = System.getProperty("apiGateway", "stg");
    protected TestContext testContext;

    public static void instantiateWebDriverObjects() {
        driverThread = ThreadLocal.withInitial(() -> {
            AutomationFactory automationThread = new AutomationFactory();
            automationThreadPool.add(automationThread);
            return automationThread;
        });
    }

    public static void loadConfigs() {
        ConfigReader.readProperties("src/test/resources/configs/credentials.properties");
    }

    public static void initialize() {
        instantiateWebDriverObjects(); // Ensure WebDriver is initialized
    }

    public static WebDriver openDriver() {
        return driverThread.get().getDriver(); // Ensure this driver is initialized in AutomationFactory
    }

    public static void closeAutomationObjects() {
        if (driverThread.get() != null) {
            driverThread.get().quitDriver();
        }
    }

    public static byte[] screenShot() {
        TakesScreenshot ts = (TakesScreenshot) openDriver();
        return ts.getScreenshotAs(OutputType.BYTES);
    }

    public static void setWebDriver(WebDriver webDriver) {
        if (driverThread.get() != null) {
            driverThread.get().setDriver(webDriver); // Assuming AutomationFactory has a setDriver method
        }
    }

    // Other existing methods...
}



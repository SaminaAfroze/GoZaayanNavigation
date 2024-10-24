package com.Assesment.utils;

import com.Assesment.base.AutomationBase;
import com.Assesment.constants.GLOBAL;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class Launcher extends AutomationBase
{
    private WebDriver webDriver;

    public Launcher(WebDriver webDriver)
    {

        this.webDriver = webDriver;

    }

    public void navigateToApplication()
    {
        webDriver.manage().deleteAllCookies();

        switch (GLOBAL.APPLICATION_ENVIRONMENT.toUpperCase()){
            case "STAGING":
            case "STAG":
            case "STG":
                webDriver.get(ConfigReader.getProperty("urlStg"));
                break;
            case "PRODUCTION":
            case "PROD":
                webDriver.get(ConfigReader.getProperty("urlProd"));
                break;
            default:
                webDriver.get(ConfigReader.getProperty("urlDev"));
        }

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    }

    public void navigateToAdminUI() {
        webDriver.manage().deleteAllCookies();
        webDriver.get(ConfigReader.getProperty("urlAdmin"));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void navigateToUpdateAdminUI() {
        webDriver.manage().deleteAllCookies();
        webDriver.get(ConfigReader.getProperty("urlUpdateAdmin"));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void navigateToIntentList() {
        webDriver.manage().deleteAllCookies();
        webDriver.get(ConfigReader.getProperty("utlIntentList"));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
    public void navigateToGoogle ()
    {
        webDriver.manage().deleteAllCookies();
        webDriver.get(ConfigReader.getProperty("urlgoogle"));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    }
}

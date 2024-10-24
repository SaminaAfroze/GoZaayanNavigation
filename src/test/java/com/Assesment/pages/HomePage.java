package com.Assesment.pages;

import com.Assesment.utils.CommonPageMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonPageMethods {

    WebDriver webDriver;

    // Locators
    @FindBy(how = How.XPATH, using = "searchResultsPage") // Button to view region and currency
    public WebElement viewRegionCurrencyButton;

    @FindBy(how = How.XPATH, using = "//div[@id='brand-header']/div[@class='container']/div[@class='row']//div[@class='region-and-currency']//div[@class='region']/ul/li[1]") // Tick for region
    public WebElement regionTick;

    @FindBy(how = How.XPATH, using = "//div[@id='brand-header']/div[@class='container']/div[@class='row']//div[@class='region-and-currency']//div[@class='currency']/ul/li[1]") // Tick for currency
    public WebElement currencyTick;

    @FindBy(how = How.XPATH, using = "/html//div[@id='searchbar']/div[@class='search-type']/span[3]/span[@class='product']") // Tour option button
    public WebElement tourOptionButton;

    @FindBy(how = How.XPATH, using = "/html//div[@id='searchbar']//div[@class='value']") // Search input field
    public WebElement searchInputField;

    @FindBy(how = How.XPATH, using = "//div[@id='searchbar']//button[@type='button']") // Search button
    public WebElement searchButton;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    // Methods to interact with elements
    public void openHomePage(String url) {
        webDriver.get(url);
    }

    public void clickViewRegionCurrencyButton() {
        viewRegionCurrencyButton.click();
    }

    public boolean isRegionTickPresent() {
        return regionTick.isDisplayed();
    }

    public boolean isCurrencyTickPresent() {
        return currencyTick.isDisplayed();
    }

    public void selectTourOption() {
        tourOptionButton.click();
    }

    public void searchForKeyword(String keyword) {
        searchInputField.clear();
        searchInputField.sendKeys(keyword);
        searchButton.click();
    }
}

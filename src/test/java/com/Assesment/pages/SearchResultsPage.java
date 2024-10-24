package com.Assesment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage {

    WebDriver webDriver;

    // Locators
    @FindBy(how = How.XPATH, using = "//div[@id='tour-list']/div[@class='container']/div[@class='row']/div[2]/div[2]//span[1]") // Location field in search results
    public List<WebElement> searchResultLocations;

    @FindBy(how = How.XPATH, using = "//div[text()='No results found']") // "No results" message
    public WebElement noResultsMessage;

    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    // Methods to interact with elements
    public boolean areResultsFromKhulna() {
        for (WebElement result : searchResultLocations) {
            if (!result.getText().contains("Khulna")) {
                return false;
            }
        }
        return true;
    }

    public boolean isNoResultsMessageDisplayed() {
        return noResultsMessage.isDisplayed();
    }
}

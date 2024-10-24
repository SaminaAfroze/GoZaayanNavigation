package com.Assesment.steps;

import com.Assesment.base.AutomationBase;
import com.Assesment.pages.HomePage;
import com.Assesment.pages.SearchResultsPage;
import com.Assesment.utils.ConfigReader;
import com.Assesment.utils.Launcher;
import com.Assesment.utils.SmartWait;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class HomePageSteps extends AutomationBase {

    Launcher launcher = new Launcher(openDriver());
    HomePage homePage = new HomePage(openDriver());
    SearchResultsPage searchResultsPage = new SearchResultsPage(openDriver());
    SmartWait smartWait = new SmartWait();

    @Given("user is on the Gozayaan homepage")
    public void userIsOnTheGozayaanHomepage() throws InterruptedException {
        System.out.println("Navigating to Gozayaan homepage");
        // Load the properties file
        ConfigReader.readProperties("src/test/resources/configs/credentials.properties");

        // Fetch URL from the config file
        String baseUrl = ConfigReader.getProperty("baseUrl");
        launcher.navigateToApplication();
        smartWait.waitUntilPageIsLoaded(5000);


    }

    @When("user clicks the {string} button")
    public void userClicksTheButton(String button) {
        if (button.equalsIgnoreCase("View Region and Currency")) {
            homePage.clickViewRegionCurrencyButton();
        }
    }

    @Then("user should see a tick beside {string} in the region section")
    public void userShouldSeeTickInRegionSection(String region) {
        Assert.assertTrue("Region tick is not displayed", homePage.isRegionTickPresent());
    }

    @Then("user should see a tick beside {string} in the currency section")
    public void userShouldSeeTickInCurrencySection(String currency) {
        Assert.assertTrue("Currency tick is not displayed", homePage.isCurrencyTickPresent());
    }

    @When("user selects the {string} option")
    public void userSelectsOption(String option) {
        if (option.equalsIgnoreCase("Tour")) {
            homePage.selectTourOption();
        }
    }

    @When("user searches for {string}")
    public void userSearchesForKeyword(String keyword) {
        String actualKeyword = "Sundarbans";
        homePage.searchForKeyword(actualKeyword);
        smartWait.waitUntilPageIsLoaded(3000); // Wait for the results to load
    }


    @Then("user should see search results with location {string}")
    public void userShouldSeeSearchResultsWithLocation(String location) {
        Assert.assertTrue("Search results do not match the expected location", searchResultsPage.areResultsFromKhulna());
    }

    @Then("user should see a message {string}")
    public void userShouldSeeAMessage(String message) {
        Assert.assertTrue("No results message is not displayed", searchResultsPage.isNoResultsMessageDisplayed());
    }
}

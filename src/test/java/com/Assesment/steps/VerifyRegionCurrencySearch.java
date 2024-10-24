package com.Assesment.steps; // Make sure this matches your project structure

import com.Assesment.base.AutomationBase;
import com.Assesment.pages.HomePage;
import com.Assesment.pages.SearchResultsPage;
import com.Assesment.utils.SmartWait;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class VerifyRegionCurrencySearch extends AutomationBase {

    HomePage homePage = new HomePage(openDriver());
    SearchResultsPage searchResultsPage = new SearchResultsPage(openDriver());
    SmartWait smartWait = new SmartWait();

    @When("user selects the {string} option")
    public void userSelectsTheOption(String option) {
        if (option.equalsIgnoreCase("Tour")) {
            homePage.selectTourOption();
        }
    }

    @And("user searches for {string}")
    public void userSearchesFor(String keyword) {
        homePage.searchForKeyword(keyword); // Call the method from homePage instance
        smartWait.waitUntilPageIsLoaded(3000); // Wait for the results to load
    }

    @Then("user should see search results with location {string}")
    public void userShouldSeeSearchResultsWithLocation(String location) {
        Assert.assertTrue("Search results do not match the expected location", searchResultsPage.areResultsFromKhulna());
    }

    @Then("the search results should contain at least {int} item")
    public void theSearchResultsShouldContainAtLeastItem(Integer itemCount) {
        int actualCount = searchResultsPage.searchResultLocations.size();
        Assert.assertTrue("Expected at least " + itemCount + " items but found " + actualCount, actualCount >= itemCount);
    }

    @Then("user should see a message {string}")
    public void userShouldSeeAMessage(String message) {
        Assert.assertTrue("No results message is not displayed", searchResultsPage.isNoResultsMessageDisplayed());
    }
}

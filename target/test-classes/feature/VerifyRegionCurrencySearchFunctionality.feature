Feature: Verify Region, Currency

  Scenario: Verify the region is set to Bangladesh
    Given user is on the Gozayaan homepage
    When user clicks the "View Region and Currency" button
    Then user should see a tick beside "Bangladesh" in the region section

  Scenario: Verify the currency is set to BDT
    Given user is on the Gozayaan homepage
    When user clicks the "View Region and Currency" button
    Then user should see a tick beside "Bangladesh" in the region section


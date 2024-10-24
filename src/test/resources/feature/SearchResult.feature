Feature: Verify  Search Functionality

Scenario: Search for tours with 'Sundarbans' keyword
Given user is on the Gozayaan homepage
When the user selects the 'Tour' option
And the user searches for 'Sundarbans'
Then the user should see search results with location 'Khulna'
And the search results should contain at least 1 item

Scenario: Verify no results appear for invalid keywords
Given user is on the Gozayaan homepage
When the user selects the 'Tour' option
And the user searches for 'InvalidKeyword'
Then the user should see a message "No results found"

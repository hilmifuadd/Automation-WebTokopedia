Feature: Product Search and Comparison

  Scenario: Search for products on Tokopedia and Bukalapak and display them in ascending order of price
    Given I am on the Tokopedia homepage
    When I search for "iPhone 15 Pro" on Tokopedia
    And I am on the Bukalapak homepage
    And I search for "iPhone 15 Pro" on Bukalapak
    Then I should see a combined list of products from both websites with their name, price, and URL sorted by ascending order of price
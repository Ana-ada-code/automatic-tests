
Feature: Address

  Background:
    Given I go to Homepage
    And   I go to Login page
    When  I login with "bgcvzqjvlxczspgoru@ytnhy.com" and "5Sdvg44h3Mdm8d2"
    And   I click Addresses

  Scenario Outline: Add address
    And   I click Create new address
    And   I insert "<alias>" "<address>" "<city>" "<zip/postal code>" "<country>" "<phone>"
    Then  I created new address
    And   I check if attributes are correct
    Examples:
      | alias | address | city   | zip/postal code | country        | phone     |
      | Ms    | Nowa    | Londek | 33-333          | United Kingdom | 123456789 |


  Scenario: Delete last address
    Then  I delete the last address
    And   I check that the address was deleted

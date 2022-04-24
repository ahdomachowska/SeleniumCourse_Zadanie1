Feature: add new address

  Scenario Outline: add new address
    Given user is logged in on account page
    When user click tile addresses
    When user click create new address
    When user fills from with data "<alias>", "<address>", "<city>", "<zipPostalCode>", "<country>", "<phone>"
    And click Save button to add new address
    Then new address is added

    Examples:

    |alias  |address|city    | zipPostalCode|country          |phone    |
    |alfons2|Downing Street|London|EC4Y 9AY      |United Kingdom   |729888555|


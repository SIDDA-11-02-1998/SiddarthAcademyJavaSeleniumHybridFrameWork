

@tag
Feature: Purchase the order from Ecommerce Website


Background:
Given I landed on Ecommerce Page

@Regression
Scenario Outline: Positive Test for Submitting the order

Given Logged in with username <name> and password <password>
When  i add product <productName> to Cart
And Checkout <productName> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page


Examples:
|name								|password  | productName|
|sidduz@gmail.com   |Virat@2024| ZARA COAT 3|

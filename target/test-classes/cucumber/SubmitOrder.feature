@tag
Feature: Purchase order from ECommerce Website

Background:
Given I landed on Ecommerce Page

@Regression
Scenario Outline: Positive Test of submitting the order
       Given  Logged in with username <username> and password <password>
       When I Add product <productname> to Cart
       And Checkout <productname> to Cart
       Then "THANKYOU FOR THE ORDER." message is displayed on Confirmationpage

    Examples:
     | username | password | productname | 
     | abhikolhe@gmail.com | Abhik@2026 | ZARA COAT 3 |
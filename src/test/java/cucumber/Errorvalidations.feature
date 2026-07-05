@tag
Feature: Invalid Login Scenarios

@ErrorValidation
Scenario Outline: Negative Test of logging in page
       Given   I landed on Ecommerce Page
       When Logged in with username <username> and password <password>
       Then "Incorrect email or password." message is displayed on Loginpage

    Examples:
     | username | password |
     | abhikolhe@gmail.com | Abhik026 |
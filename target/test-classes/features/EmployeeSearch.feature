Feature: Employee Search

  Background: these are common steps
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on employee list option

  @smoke
  Scenario: Search Employee by ID
    #Given user is navigated to HRMS application
    When user enters valid employee id
    And user clicks on search button
    Then user is able to see the employee

  @regression
  Scenario: Search Employee by Name
    #Given user is navigated to HRMS application
    When user enters valid employee name
    And user clicks on search button
    Then user is able to see the employee
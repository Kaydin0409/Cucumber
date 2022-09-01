Feature: Login feature

  @sprint29
  Scenario: Valid admin login
    Given user is navigated to HRMS application
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in

   @smoke @sprint29
  Scenario: Valid ess login
    Given user is navigated to HRMS application
    When user enters ess username and password
    And user clicks on login button
    Then ess user is successfully logged in

  @regression
  Scenario: invalid login
    Given user is navigated to HRMS application
    When user enters invalid admin username and password
    And user clicks on login button
    Then admin user is able to see error message
  @login
  Scenario Outline: Negative login test
    When user enters different "<username>" and "<password>" and verify the "<error>"
    Examples:
     | username | password  | error|
     |admin     |xyz        |Invalid credentials|
     |Cristiano |Hum@n      |Invalid credentials|
     |          |Hum@nhrm123|Username cannot be empty|
     |Admin     |           |Password cannot be empty|
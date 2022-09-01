Feature: THis feature is going to add employees in HRMS application

  Background:  #common steps
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on add employee option
  @smoke
  Scenario: Add an employee
    When user enters firstName, middleName, and lastName
    And user clicks on save button
    Then employee added successfully

  @test
  Scenario: Adding one employee from cucumber feature file
    When user enters "Oman", "Tagai", and "Gihid"
    And user clicks on save button
    Then employee added successfully

  @dataprovider  #this way runs the hooks multiple times, along with background steps
  Scenario Outline: Adding multiple employees from cucumber feature file
    When user entered "<firstName>", "<middleName>", and "<lastName>"
    And user clicks on save button
    Then employee added successfully
    Examples:
    |firstName|middleName|lastName|
    |Romid    |MS        |Zarif   |
    |Rokan    |MS        |Elisa   |
    |Mama     |Tarindi   |Jamu    |

  @datatable
  Scenario: Adding multiple employees using data table
    When user adds multiple employees and verify they are added
      |firstName|middleName|lastName |
      |khryswana|MS        |jaman    |
      |zamis    |MS        |Gaukhar  |
      |tamir    |MS        |microsoft|

  @excelfile
  Scenario: Adding employees from excel file
    When user adds multiple employees from excel file using "employeeData" sheet and verify the employee has been added










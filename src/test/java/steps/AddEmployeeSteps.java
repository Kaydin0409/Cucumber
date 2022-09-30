package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    String firstNameUI;
    String middleName;
    String lastNameUI;
    String empIDUI;
    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        click(dash.addEmployeeOption);
    }
    @When("user enters firstName, middleName, and lastName")
    public void user_enters_first_name_middle_name_and_last_name() {
        sendText(addEmployeePage.firstName, "Gisel");
        sendText(addEmployeePage.middleName,"Francis");
        sendText(addEmployeePage.lastName, "Arif");
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click(addEmployeePage.saveButton);
    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        //homework: verify added employee
        System.out.println("Employee added");
    }


    @When("user enters {string}, {string}, and {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
        this.firstNameUI =firstName;
        this.middleName=middleName;
        this.lastNameUI =lastName;
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @When("user entered {string}, {string}, and {string}")
    public void user_entered_and(String fn, String mn, String ln) {
        sendText(addEmployeePage.firstName, fn);
        sendText(addEmployeePage.middleName, mn);
        sendText(addEmployeePage.lastName, ln);
    }

    @When("user adds multiple employees and verify they are added")
    public void user_adds_multiple_employees_and_verify_they_are_added(DataTable dataTable) throws InterruptedException {
        List<Map<String,String>> employeeNames=dataTable.asMaps();

        for(Map<String,String> employee:employeeNames){
            String firstNameValue= employee.get("firstName");
            String middleNameValue=employee.get("middleName");
            String lastNameValue=employee.get("lastName");

            sendText(addEmployeePage.firstName, firstNameValue);
            sendText(addEmployeePage.middleName, middleNameValue);
            sendText(addEmployeePage.lastName, lastNameValue);

            click(addEmployeePage.saveButton);

            //verify employee added

            Thread.sleep(2000);
            click(dash.addEmployeeOption);
            Thread.sleep(2000);

        }


    }

    @When("user adds multiple employees from excel file using {string} sheet and verify the employee has been added")
    public void user_adds_multiple_employees_from_excel_file_using_sheet_and_verify_the_employee_has_been_added(String sheetName) throws InterruptedException {

        List<Map<String, String>> newEmployees = ExcelReader.excelListIntoMap(Constants.TEST_DATA_FILEPATH, sheetName);
        Iterator<Map<String, String>> itr = newEmployees.iterator();
        while (itr.hasNext()) {
            Map<String, String> mapNewEmp = itr.next();

            sendText(addEmployeePage.firstName, mapNewEmp.get("firstName"));
            sendText(addEmployeePage.middleName, mapNewEmp.get("middleName"));
            sendText(addEmployeePage.lastName, mapNewEmp.get("lastName"));

            sendText(addEmployeePage.photograph, mapNewEmp.get("photograph"));
            if (!addEmployeePage.checkBox.isSelected()) {
                click(addEmployeePage.checkBox);
            }

            sendText(addEmployeePage.usernameEmployee, mapNewEmp.get("username"));
            sendText(addEmployeePage.passwordEmployee, mapNewEmp.get("password"));
            sendText(addEmployeePage.confirmPasswordEmployee, mapNewEmp.get("confirmPassword"));

            String empIdValue = addEmployeePage.empIdLoc.getAttribute("value");
            System.out.println(empIdValue);

            click(addEmployeePage.saveButton);

            //till this point we have added the employee and captured the emp id
            Thread.sleep(4000);

            //  click(dash.employeeListOption);
            jsClick(dash.employeeListOption);
            Thread.sleep(2000);
            sendText(emp.idEmployeeSearch, empIdValue);
            click(emp.searchButton);

            //verify the details of my employee
            //verifying firstname middlename and lastname

            List<WebElement> rowData =  driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));

            for (int i=0; i<rowData.size(); i++){
                System.out.println("I am inside the loop");

                //this line is going to return text of every single row for the element available in it
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);

                String expectedData =  empIdValue + " " + mapNewEmp.get("firstName") + " " +
                        mapNewEmp.get("middleName") + " " + mapNewEmp.get("lastName");

                System.out.println(expectedData);
                Assert.assertEquals(expectedData, rowText);

            }
            Thread.sleep(2000);
            click(dash.addEmployeeOption);


        }
    }

    @And("user grabs employee ID")
    public void userGrabsEmployeeID() {
        empIDUI =addEmployeePage.empIdLoc.getAttribute("value");

    }

    @Then("fetch the data from backend and verify employee has been added")
    public void fetchTheDataFromBackendAndVerifyEmployeeHasBeenAdded() {
        String query= DBQueries.FETCH_FNAME_LNAME+ empIDUI +"'";
        List<Map<String,String>> dbData= DbUtils.fetchDbData(query);
        //from first row we are getting employee firstname column
        String firstNamefromDB=dbData.get(0).get("emp_firstname");
        String lastNamefromDB=dbData.get(0).get("emp_lastname");

        Assert.assertEquals(firstNameUI,firstNamefromDB);
        Assert.assertEquals(lastNameUI,lastNamefromDB);
    }
}

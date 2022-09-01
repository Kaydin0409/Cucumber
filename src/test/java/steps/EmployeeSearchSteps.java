package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.EmployeeInformationPage;
import utils.CommonMethods;

public class EmployeeSearchSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        DashboardPage dashboard=new DashboardPage();
        click(dashboard.pimOption);
    }
    @When("user clicks on employee list option")
    public void user_clicks_on_employee_list_option() {
//        DashboardPage dashboardPage=new DashboardPage();
        click(dash.employeeListOption);
    }
    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
//        EmployeeInformationPage employeePage=new EmployeeInformationPage();
        sendText(emp.idEmployeeSearch,"30249233");

    }
    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
//        EmployeeInformationPage employeePage=new EmployeeInformationPage();
        click(emp.searchButton);
    }
    @Then("user is able to see the employee")
    public void user_is_able_to_see_the_employee() {
        System.out.println("User is able to see employee");
    }

    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
        sendText(emp.nameEmployeeSearch,  "test");
    }

}

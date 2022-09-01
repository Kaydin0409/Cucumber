package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {

    @Then("verify all the dashboard tabs")
    public void verify_all_the_dashboard_tabs(DataTable dataTable) {
        List<String> expectedTabs=dataTable.asList();

        List<String> actualTabs=new ArrayList<>();


        for(WebElement tab: dash.dashboardTabs){
            actualTabs.add(tab.getText());
        }
        System.out.println(actualTabs);
        System.out.println(expectedTabs);
        Assert.assertTrue(expectedTabs.equals(actualTabs));
    }
}

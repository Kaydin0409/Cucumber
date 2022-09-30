package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //use this runner class for your local testing

        //features we use to provide the path of all the features file
        features = "src/test/resources/features/",
        //glue is where we find implementations for gherkin steps
        //we provide the path of package where we defined all the steps
            glue = "steps",
            dryRun = false,
            monochrome=true,
            tags= "@db",
            plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json", "rerun:target/failed.txt"}
        //monochrome cleans your console output for Cucumber test if it has irrelevant or unreadable character in it
        //recommended to always keep it true
        //dryRun will give us just the steps that need to be implemented
        //when you use pretty keyword under plugins it shows all the steps executed in console

        //rerun plugin is going to capture all the scenarios that failed during execution

)
public class RunnerClass {

}

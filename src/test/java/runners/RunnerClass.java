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
            tags= "@sprint29",
            plugin = {"pretty", "html:target/cucumber.html"}
        //monochrome cleans your console output for Cucumber test if it has irrelevant or unreadable character in it
        //recommended to always keep it true
        //dryRun will give us just the steps that need to be implemented
        //when you use pretty keyword under plugins it shows all the steps executed in console

)
public class RunnerClass {

}

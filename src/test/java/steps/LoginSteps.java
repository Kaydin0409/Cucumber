package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginSteps extends CommonMethods {

//    public static WebDriver driver;
    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
       /* WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login"); //don't hard code anything!  will change later
        Thread.sleep(2000);*/
        openBrowserAndLaunchApplication();
    }
    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
//        WebElement usernameTextField=driver.findElement(By.id("txtUsername"));
//        LoginPage login=new LoginPage();
        sendText(login.usernameTextField, ConfigReader.getPropertyValue("username"));

//        WebElement passwordTextField=driver.findElement(By.id("txtPassword"));
        sendText(login.passwordTextField,ConfigReader.getPropertyValue("password"));
    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() throws InterruptedException {
//        WebElement loginButton= driver.findElement(By.id("btnLogin"));
       /* loginButton.click();
        Thread.sleep(2000);*/
//        LoginPage login=new LoginPage();
        click(login.loginButton);
    }
    @Then("admin user is successfully logged in")
    public void admin_user_is_successfully_logged_in() {
        System.out.println("Test passed");
        //closeBrowser();
    }

    @When("user enters ess username and password")
    public void user_enters_ess_username_and_password() {
//        WebElement usernameTextField=driver.findElement(By.id("txtUsername"));
//        LoginPage login=new LoginPage();
        sendText(login.usernameTextField, "admin");

//        WebElement passwordTextField=driver.findElement(By.id("txtPassword"));
        sendText(login.passwordTextField,"Hum@nhrm123");
    }
    @Then("ess user is successfully logged in")
    public void ess_user_is_successfully_logged_in() {
        System.out.println("Test Passed");
    }

    @When("user enters invalid admin username and password")
    public void user_enters_invalid_admin_username_and_password() {
//        WebElement usernameTextField=driver.findElement(By.id("txtUsername"));
//        LoginPage login=new LoginPage();
        sendText(login.usernameTextField, "admi");

//        WebElement passwordTextField=driver.findElement(By.id("txtPassword"));
        sendText(login.passwordTextField,"Hum");
    }
    @Then("admin user is able to see error message")
    public void admin_user_is_able_to_see_error_message() {
        System.out.println("Test failed");
    }

    @When("user enters different {string} and {string} and verify the {string}")
    public void user_enters_different_and_and_verify_the(String uname, String pwd, String error) {
        sendText(login.usernameTextField, uname);
        sendText(login.passwordTextField, pwd);
        click(login.loginButton);

        String errorActual=login.errorMessage.getText();
        Assert.assertEquals(error,errorActual);

    }


}

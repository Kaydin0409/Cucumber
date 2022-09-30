package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardCodedExamples {
    String baseURI= RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    //CRUD operations are performed
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjQ1NDMwNjQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY2NDU4NjI2NCwidXNlcklkIjoiNDQwNCJ9.COeaI6S1HoqNrgR2BO5v2eCfML6ToT0fj4yROv4oHsM";

    public static String employee_id;
    @Test
    //to create regular/normal employee
    public void acreateEmployee(){
        //prepare the request -- POST
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token).body("{\n" +
                "  \"emp_firstname\": \"Oguz\",\n" +
                "  \"emp_lastname\": \"Bond\",\n" +
                "  \"emp_middle_name\": \"A\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1988-09-25\",\n" +
                "  \"emp_status\": \"active\",\n" +
                "  \"emp_job_title\": \"SDET\"\n" +
                "}");

        //hitting the endpoint (sending the request) After you send the request you'll be returned with response variable
       Response response = preparedRequest.when().post("/createEmployee.php");
       response.prettyPrint();   //this prints all the data in the response in console

        //assertions and validation
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("A"));
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Oguz"));
        response.then().assertThat().body("Employee.emp_lastname", equalTo("Bond"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));

        //verify response headers (can see in console on postman)
        //don't use equalTo for verifying header (only for verifying in body)
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

        //to get the employee id from the body
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }

    @Test
    public void bgetCreatedEmployee(){
        //prepare the Request
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                                                .header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("employee.employee_id", equalTo(employee_id));

    }

    @Test
    public void cupdateEmployee(){
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token).body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"Baris\",\n" +
                        "  \"emp_lastname\": \"Kasap\",\n" +
                        "  \"emp_middle_name\": \"B\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1979-09-25\",\n" +
                        "  \"emp_status\": \"terminated\",\n" +
                        "  \"emp_job_title\": \"SDET\"\n" +
                        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");
        response.prettyPrint();

        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void dGetUpdatedEmployee(){
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token).queryParam("employee_id",employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("employee.employee_id", equalTo(employee_id));
        response.then().assertThat().body("employee.emp_firstname", equalTo("Baris"));
    }
}

package APIsteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;
import utils.Constants;

import static io.restassured.RestAssured.given;


public class GenerateTokenSteps {
    public static String token;
    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"email\": \"Baris12@gmail.com\",\n" +
                        "  \"password\": \"baris@123\"\n" +
                        "}");
        Response response = preparedRequest.when().post(APIConstants.GENERATE_TOKEN_URI);

        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);
    }
}

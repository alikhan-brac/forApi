package qa.restapi.test;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import io.restassured.RestAssured;
import qa.restapi.model.User;
import qa.restapi.util.Constants;
import qa.restapi.util.JsonDataPuller;
import static qa.restapi.util.HttpStatus.*;

public class RestApiTest {
	private static Response response;
	private static String urlForData = "";
	public static String postTitle = "";
	public static String postBody = "";

	@Test
	public void movieTitleCheck() {

		// Step#1
		urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.configData, "/post");
		get(urlForData).then().assertThat().statusCode(OK.getValue());

		// Step#2
		urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.configData, "/post")
				+ JsonDataPuller.dataPuller(Constants.testData, "/postIDToGetSecondStep");
		get(urlForData).then().assertThat().statusCode(OK.getValue())
				.body("userId",
						equalTo(Integer.valueOf(JsonDataPuller.dataPuller(Constants.testData, "/userIDToCompare"))))
				.body("id", equalTo(Integer.valueOf(JsonDataPuller.dataPuller(Constants.testData, "/postIDToCompare"))))
				.body("title", notNullValue()).body("body", notNullValue());

		// Step#3
		urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.configData, "/post")
				+ JsonDataPuller.dataPuller(Constants.testData, "/postIDToGetThirdStep");
		get(urlForData).then().assertThat().statusCode(NOT_FOUND.getValue()).body("isEmpty()", Matchers.is(true));

		// --step4
		urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.configData, "/post");
		response = RestAssured.given().contentType("application/json").body(Constants.createPost()).when()
				.post(urlForData);
		response.then().assertThat().statusCode(CREATED.getValue()).body("title", equalTo(postTitle))
				.body("body", equalTo(postBody))
				.body("userId", equalTo(JsonDataPuller.dataPuller(Constants.testData, "/userIDForfourthStep")))
				.body("id", notNullValue());

		// Step#5
		urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.configData, "/user");
		response = RestAssured.get(urlForData);
		response.then().assertThat().statusCode(OK.getValue()).contentType(ContentType.JSON);
		List<User> userList = response.then().extract().body().jsonPath().getList(".", User.class);
		User userFromApi = userList.stream().filter(p -> p.getId().equals("5")).findAny().orElse(null);
		User userFromTestData = Constants
				.userToCompare(JsonDataPuller.dataPuller(Constants.testData, "/userIdToCompare"));
		assertTrue(userFromApi.equals(userFromTestData), "Respective user data mismatched between api and testData");

		// Step#6
		urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.configData, "/user")
				+ JsonDataPuller.dataPuller(Constants.testData, "/userToCheckAtSixthStep");

		response = RestAssured.get(urlForData);
		response.then().assertThat().statusCode(OK.getValue());
		User specificUserFromApi = response.getBody().as(User.class);
		assertTrue(userFromApi.equals(specificUserFromApi), "Respective user data mismatched between api and testData");
	}
}

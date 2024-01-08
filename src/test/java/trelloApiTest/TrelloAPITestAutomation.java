package trelloApiTest;

import apiBuilders.PostAPIBuilder;
import apiConfigs.Endpoints;
import apiConfigs.HeaderConfigs;
import apiVerifications.APIVerification;
import baseTest.BaseTest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.FileandEnv;

public class TrelloAPITestAutomation extends BaseTest {

	@Test
	public void APIAutomation() throws UnirestException {
		HeaderConfigs headerConfigs = new HeaderConfigs();
		FileandEnv.envAndFile();
		PostAPIBuilder builder = new PostAPIBuilder();
		String boardName = "NewTrelloBoard";
		String cardName = "NewTrelloCard";

		test.log(LogStatus.INFO, "Test is started.");
		test.log(LogStatus.INFO, "Creating board.");
		// Creating board
		Response response = RestAssured.given().contentType("application/json")
				.headers(headerConfigs.headersWithToken(boardName)).queryParams(builder.postRequestBody(boardName))
				.when().post(Endpoints.apiEndpoints.CREATE_BOARD).then().extract().response();
		test.log(LogStatus.INFO, "Getting id from board.");
		// Getting id from board
		String idList = RestAssured.given().headers(headerConfigs.defaultHeaders())
				.queryParams(builder.getRequestBody()).get(Endpoints.apiEndpoints.GET_BOARD).body().jsonPath()
				.get("id");

		test.log(LogStatus.INFO, "Creating 2 cards.");
		// Creating 2 card
		for (int i = 0; i < 2; i++)
			RestAssured.given().headers(headerConfigs.headerForCard(idList))
					.queryParams(builder.postRequestBody(cardName)).when().post(Endpoints.apiEndpoints.CREATE_CARD)
					.then().extract().response();

		test.log(LogStatus.INFO, "Updating card.");
		// Update card
		RestAssured.given().headers(headerConfigs.headerForCard(idList)).queryParams(builder.postRequestBody(cardName))
				.when().put(Endpoints.apiEndpoints.PUT_CARD).then().extract().response();

		test.log(LogStatus.INFO, "Deleting card.");
		// Delete card
		RestAssured.given().headers(headerConfigs.headerForCard(idList)).queryParams(builder.postRequestBody(cardName))
				.when().delete(Endpoints.apiEndpoints.DELETE_CARD).then().extract().response();

		test.log(LogStatus.INFO, "Deleting board.");
		// Delete board
		RestAssured.given().headers(headerConfigs.headersWithToken(boardName))
				.queryParams(builder.postRequestBody(boardName)).when().delete(Endpoints.apiEndpoints.DELETE_BOARD)
				.then().extract().response();

		APIVerification.responseCodeValidation(response, 200);
		APIVerification.responseTimeValidation(response);

		test.log(LogStatus.INFO, "Test Finished.");
	}
}

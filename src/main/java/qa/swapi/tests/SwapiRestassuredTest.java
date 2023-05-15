package qa.swapi.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import qa.swapi.model.FilmInfo;
import qa.swapi.util.Constants;
import qa.swapi.util.JsonDataPuller;
import qa.swapi.util.RandomSelection;
import qa.swapi.util.RestassuredApiUtil;
import qa.swapi.util.StaticDataProvider;

public class SwapiRestassuredTest {
	private static Response response;

	@Test(priority = 1)
	public void movieTitleCheck() {
		String urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.apiRequests, "/allFilms");
		response = RestassuredApiUtil.getResponse(urlForData);
		assertEquals(response.getStatusCode(), Constants.statusCode, "Status code is NOT 200");
		List<FilmInfo> filmTitleFromApi = RestassuredApiUtil.getMovieNameFromApi(response);
		List<FilmInfo> filmTitleFromTestdata = Constants.getMovieNameFromTestData(Constants.movieTitles);
		filmTitleFromApi.retainAll(filmTitleFromTestdata);
		assertTrue(filmTitleFromTestdata.containsAll(filmTitleFromApi),
				"Response NOT containing movie titles as required.");
	}

	@Test(priority = 2, dataProvider = "dataForTest2", dataProviderClass = StaticDataProvider.class)
	public void producerNameCheck(String filmId) {
		String urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.apiRequests, "/allFilms") + filmId;
		response = RestassuredApiUtil.getResponse(urlForData);
		assertEquals(response.getStatusCode(), Constants.statusCode, "Status code is NOT 200");
		assertEquals(RestassuredApiUtil.getProducerNameFromApi(response),
				JsonDataPuller.dataPuller(Constants.testData, "/producerName"), "Producer Name NOT matching.");
	}

	@Test(priority = 3)
	public void peopleCheck() {
		String urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.apiRequests, "/allPeople")
				+ RandomSelection.randomPeopleSelection();
		response = RestassuredApiUtil.getResponse(urlForData);
		assertEquals(response.getStatusCode(), Constants.statusCode, "Status code is NOT 200");
		assertTrue(RestassuredApiUtil.getFilmsOfTheCharacter(response), "Producer Name NOT matching.");
	}
}

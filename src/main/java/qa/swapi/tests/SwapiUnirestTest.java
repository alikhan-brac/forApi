package qa.swapi.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import qa.swapi.model.FilmInfo;
import qa.swapi.util.Constants;
import qa.swapi.util.JsonDataPuller;
import qa.swapi.util.RandomSelection;
import qa.swapi.util.StaticDataProvider;
import qa.swapi.util.UnirestApiUtil;

public class SwapiUnirestTest {
	private static HttpResponse<JsonNode> response;

	@Test(priority = 1)
	public void movieTitleCheck() {
		String urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.apiRequests, "/allFilms");
		response = UnirestApiUtil.getResponse(urlForData);
		assertEquals(response.getStatus(), Constants.statusCode, "Status code is NOT 200");
		List<FilmInfo> filmTitleFromApi = UnirestApiUtil.getMovieNameFromApi(response);
		List<FilmInfo> filmTitleFromTestdata = Constants.getMovieNameFromTestData(Constants.movieTitles);
		filmTitleFromApi.retainAll(filmTitleFromTestdata);
		assertTrue(filmTitleFromTestdata.containsAll(filmTitleFromApi),
				"Response NOT containing movie titles as required.");
	}

	@Test(priority = 2, dataProvider = "dataForTest2", dataProviderClass = StaticDataProvider.class)
	public void producerNameCheck(String filmId) {
		String urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.apiRequests, "/allFilms") + filmId;
		response = UnirestApiUtil.getResponse(urlForData);
		assertEquals(response.getStatus(), Constants.statusCode, "Status code is NOT 200");
		assertEquals(UnirestApiUtil.getProducerNameFromApi(response),
				JsonDataPuller.dataPuller(Constants.testData, "/producerName"), "Producer Name NOT matching.");
	}

	@Test(priority = 3)
	public void peopleCheck() {
		String urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.apiRequests, "/allPeople")
				+ RandomSelection.randomPeopleSelection();
		response = UnirestApiUtil.getResponse(urlForData);
		assertEquals(response.getStatus(), Constants.statusCode, "Status code is NOT 200");
		assertTrue(UnirestApiUtil.getFilmsOfTheCharacter(response), "Producer Name NOT matching.");
	}

}

package qa.swapi.util;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import qa.swapi.model.FilmInfo;

public class RestassuredApiUtil {
	private static Response response;

	public static Response getResponse(String urlForData) {
		response = RestAssured.get(urlForData);
		return response;
	}

	public static List<FilmInfo> getMovieNameFromApi(Response response) {
		List<FilmInfo> filmTitle = new ArrayList<FilmInfo>();
		for (int i = 0; i < response.jsonPath().getList("results.title").size(); i++) {
			filmTitle.add(new FilmInfo(response.jsonPath().get("results.title" + i).toString()));
		}
		return filmTitle;
	}

	public static String getProducerNameFromApi(Response response) {
		String filmProducer = null;
		filmProducer = response.jsonPath().get("producer").toString();
		return filmProducer;
	}

	public static boolean getFilmsOfTheCharacter(Response response) {
		boolean atLeastOneFilm = false;
		String filmNames = response.jsonPath().get("films").toString();
		if (!(filmNames == null)) {
			atLeastOneFilm = true;
		}
		return atLeastOneFilm;
	}
}

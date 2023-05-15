package qa.swapi.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import qa.swapi.model.FilmInfo;

public class UnirestApiUtil {
	private static HttpResponse<JsonNode> response;

	public static HttpResponse<JsonNode> getResponse(String urlForData) {
		try {
			response = Unirest.get(urlForData).asJson();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return response;
	}

	public static List<FilmInfo> getMovieNameFromApi(HttpResponse<JsonNode> response) {
		List<FilmInfo> filmTitle = new ArrayList<FilmInfo>();
		JSONObject object = response.getBody().getObject();
		JSONArray results = object.getJSONArray("results");
		for (int i = 0; i < results.length(); i++) {
			JSONObject node = results.getJSONObject(i);
			String filmName = node.getString("title");
			filmTitle.add(new FilmInfo(filmName));
		}
		return filmTitle;
	}

	public static String getProducerNameFromApi(HttpResponse<JsonNode> response) {
		String filmProducer = null;
		JSONObject object = response.getBody().getObject();
		filmProducer = object.getString("producer");
		return filmProducer;
	}

	public static boolean getFilmsOfTheCharacter(HttpResponse<JsonNode> response) {
		boolean atLeastOneFilm = false;
		List<FilmInfo> filmTitle = new ArrayList<FilmInfo>();
		JSONObject object = response.getBody().getObject();
		JSONArray results = object.getJSONArray("films");
		for (int i = 0; i < results.length(); i++) {
			String filmName = results.getString(i);
			filmTitle.add(new FilmInfo(filmName));
		}
		if (!(filmTitle == null)) {
			atLeastOneFilm = true;
		}
		return atLeastOneFilm;
	}

}

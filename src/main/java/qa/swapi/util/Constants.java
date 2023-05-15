package qa.swapi.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.fasterxml.jackson.core.JsonParseException;

import qa.swapi.model.FilmInfo;

public class Constants {
	public static final int statusCode = 200;
	public static final String configData = ".\\src\\test\\resources\\config.json";
	public static final String apiRequests = ".\\src\\test\\resources\\apirequest.json";
	public static final String movieTitles = ".\\src\\test\\resources\\movieTitles.json";
	public static final String testData = ".\\src\\test\\resources\\testData.json";
	public static final String selectedMovie = ".\\src\\test\\resources\\selectedMovie.json";

	public static final List<FilmInfo> getMovieNameFromTestData(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		List<FilmInfo> filmTitle = null;
		try {
			filmTitle = mapper.readValue(new File(fileName), new TypeReference<ArrayList<FilmInfo>>() {
			});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filmTitle;
	}
}

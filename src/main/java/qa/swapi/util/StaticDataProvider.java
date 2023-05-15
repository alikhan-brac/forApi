package qa.swapi.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;

public class StaticDataProvider {

	@DataProvider(name = "dataForTest2")
	public static Object[][] createData() throws IOException {
		List<String> filmId = new ArrayList<String>();
		String text = new String(Files.readAllBytes(Paths.get(Constants.selectedMovie)), StandardCharsets.UTF_8);
		JSONObject object = new JSONObject(text);
		JSONArray results = object.getJSONArray("filmId");
		for (int i = 0; i < results.length(); i++) {
			String filmName = results.getString(i);
			filmId.add(filmName);
		}
		Object[][] hashMapObj = new Object[filmId.size()][1];
		for (int i = 0; i < filmId.size(); i++) {
			hashMapObj[i][0] = filmId.get(i);
		}
		return hashMapObj;
	}
}

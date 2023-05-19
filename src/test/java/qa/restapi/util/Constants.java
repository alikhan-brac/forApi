package qa.restapi.util;

import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import qa.restapi.model.User;
import qa.restapi.test.RestApiTest;

public class Constants {
	public static final String configData = ".\\src\\test\\resources\\config.json";
	public static final String testData = ".\\src\\test\\resources\\testData.json";

	public static final User userToCompare(String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User();
		try {
			user = objectMapper.readValue(jsonString, User.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static final HashMap<String, String> createPost() {
		RestApiTest.postTitle = LipsumGenerator
				.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(testData, "/randomWordCount")));
		RestApiTest.postBody = LipsumGenerator
				.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(testData, "/randomWordCount")));
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("title", RestApiTest.postTitle);
		data.put("body", RestApiTest.postBody);
		data.put("userId", JsonDataPuller.dataPuller(testData, "/userIDForfourthStep"));
		return data;
	}

}

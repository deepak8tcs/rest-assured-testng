package utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	
	private static final String JSON_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\json\\";
	
	public static <T> T getJavaObjectFromJsonFile(String json, Class<T> object){
		T register = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			register =  mapper.readValue(new File(JSON_PATH+json), object);
			//readValue is a method to deserialize JSON content from given file into given Java type.
		} catch (JsonParseException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return register;

	}

	public static String getJsonStringFromObject(Object object){
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
		return jsonString;

	}

	public static <T> T getJavaObjectFromJsonString(String jsonString, Class<T> object){
		T pojoObject = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			pojoObject =  mapper.readValue(jsonString, object);
		} catch (JsonParseException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return pojoObject;

	}

}

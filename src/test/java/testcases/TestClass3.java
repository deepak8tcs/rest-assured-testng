package testcases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
import object.modifier.CustomerObjectModifer;
import request.pojo.RegisterRequest;
import response.pojo.RegisterResponse;
import test.base.TestBase;
import utils.JsonReader;

public class TestClass3 extends TestBase {


	private RegisterRequest registerRequest;


	public RegisterRequest getRegisterRequest() {
		return registerRequest;
	}

	public void setRegisterRequest(RegisterRequest registerRequest) {
		this.registerRequest = registerRequest;
	}

	CustomerObjectModifer coModifier = null;

	@BeforeClass
	public void setUp() {

		coModifier = new CustomerObjectModifer();

	}



	@Test
	public void userRegistration1() throws Exception
	{		

		RegisterRequest register =JsonReader.getJavaObjectFromJsonFile("register.json", RegisterRequest.class);
		setRegisterRequest(register);	

		HashMap<String,String> regData = new HashMap<String, String>();
		regData.put("UserName", "random");
		regData.put("Email", "random");
		for(Map.Entry<String, String> entry: regData.entrySet())
			coModifier.modifyRegistrationObject(getRegisterRequest(),entry.getKey(), entry.getValue());

		Response response= postRequest(JsonReader.getJsonStringFromObject(getRegisterRequest()),"/register");

		Assert.assertEquals(response.getStatusCode(), 201);
		RegisterResponse regResponse = JsonReader.getJavaObjectFromJsonString(response.getBody().asString(), RegisterResponse.class);
		Assert.assertEquals(regResponse.getSuccessCode(), "OPERATION_SUCCESS", "Correct Success code was not returned");
	}


	@Test
	public void userRegistration2() throws Exception
	{		

		RegisterRequest register =JsonReader.getJavaObjectFromJsonFile("register.json", RegisterRequest.class);
		setRegisterRequest(register);	

		HashMap<String,String> regData = new HashMap<String, String>();
		regData.put("UserName", "random");
		regData.put("Email", "random");
		for(Map.Entry<String, String> entry: regData.entrySet())
			coModifier.modifyRegistrationObject(getRegisterRequest(),entry.getKey(), entry.getValue());

		Response response= postRequest(JsonReader.getJsonStringFromObject(getRegisterRequest()),"/register");

		Assert.assertEquals(response.getStatusCode(), 201);
		RegisterResponse regResponse = JsonReader.getJavaObjectFromJsonString(response.getBody().asString(), RegisterResponse.class);
		Assert.assertEquals(regResponse.getSuccessCode(), "OPERATION_SUCCESS", "Correct Success code was not returned");
	}

}






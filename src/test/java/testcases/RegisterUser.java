package testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import object.modifier.CustomerObjectModifer;
import request.pojo.RegisterRequest;
import response.pojo.RegisterResponse;
import test.base.TestBase;
import utils.JsonReader;

public class RegisterUser extends TestBase {


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


	//@Test
	public void registerUser() throws Exception
	{		

		RegisterRequest register =JsonReader.getJavaObjectFromJsonFile("user.json", RegisterRequest.class);
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






package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import request.pojo.PetRequest;
import test.base.TestBase;
import utils.JsonReader;

public class GetPetDetailsById extends TestBase {

	@Test
	public void getPetDetails() throws Exception
	{		
		Response response= getRequest("v2/pet/"+getId());

		Assert.assertEquals(response.getStatusCode(), 200);

		PetRequest petResponse = JsonReader.getJavaObjectFromJsonString(response.getBody().asString(), PetRequest.class);
		Assert.assertEquals(petResponse.getTags().get(0).getId().intValue(), 0);
		Assert.assertTrue(petResponse.getTags().size() > 0);
		Assert.assertEquals(petResponse.getTags().stream().filter(x -> x.getId() >=0).count(), 2);

	}


}






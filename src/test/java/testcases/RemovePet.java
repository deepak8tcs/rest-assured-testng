package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import test.base.TestBase;

public class RemovePet extends TestBase {


	@Test
	public void deletePet() throws Exception
	{		

		if(getId() == null)
			setUpPetData();

		Response response= deleteRequest("v2/pet/"+getId());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(response.getBody().asString().isEmpty());		
		setId(null);

	}


}






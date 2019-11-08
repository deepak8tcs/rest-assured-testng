package object.modifier;

import request.pojo.RegisterRequest;
import utils.CommonFunctions;

public class CustomerObjectModifer {
	
	public void modifyRegistrationObject(RegisterRequest registerRequest, String param, String value) throws Exception {

		switch(param) {
		case "UserName":
			registerRequest.setUserName(CommonFunctions.randomAlphaNumericString(10));
			break;
		case "Email":
			registerRequest.setEmail(CommonFunctions.randomAlphaNumericString(10)+"@gmail.com");
			break;
		default:
			throw new Exception("wrong param provided");			
		}


	}

}

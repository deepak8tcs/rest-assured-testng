package response.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterResponse {

@JsonProperty("SuccessCode")
private String successCode;

@JsonProperty("Message")
private String message;

public String getSuccessCode() {
	return successCode;
}

public void setSuccessCode(String successCode) {
	this.successCode = successCode;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}


}
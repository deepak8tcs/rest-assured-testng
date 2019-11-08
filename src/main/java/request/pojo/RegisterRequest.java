package request.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequest {

@JsonProperty("UserName")
private String userName;

@JsonProperty("Email")
private String email;

@JsonProperty("FirstName")
private String firstName;

@JsonProperty("LastName")
private String lastName;

@JsonProperty("Password")
private String password;


@JsonProperty("UserName")
public String getUserName() {
return userName;
}

@JsonProperty("UserName")
public void setUserName(String userName) {
this.userName = userName;
}

@JsonProperty("Email")
public String getEmail() {
return email;
}

@JsonProperty("Email")
public void setEmail(String email) {
this.email = email;
}

@JsonProperty("FirstName")
public String getFirstName() {
return firstName;
}

@JsonProperty("FirstName")
public void setFirstName(String firstName) {
this.firstName = firstName;
}

@JsonProperty("LastName")
public String getLastName() {
return lastName;
}

@JsonProperty("LastName")
public void setLastName(String lastName) {
this.lastName = lastName;
}

@JsonProperty("Password")
public String getPassword() {
return password;
}

@JsonProperty("Password")
public void setPassword(String password) {
this.password = password;
}


}
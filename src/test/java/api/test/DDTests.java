package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviders.class)
public void	testPostUser(String userID,String userName, String fname, String lname,String useremail, String pwd, String ph){
	UserPojo userPayload = new UserPojo();	
	
	userPayload.setId(Integer.parseInt(userID));
	userPayload.setUsername(userName);
	userPayload.setFirstName(fname);
	userPayload.setLastName(lname);
	userPayload.setEmail(useremail);
	userPayload.setPassword(pwd);
	userPayload.setPhone(ph);
	
	
	
	Response response =	UserEndPoints.createUser(userPayload);
		//response.then().log().all();
		response.statusCode();
	   
}
	
@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
public void testDeleteUser(String userName)   {
	Response response = UserEndPoints.deleteUser(userName);
	Assert.assertEquals(response.getStatusCode(),200);
}
	
	

}

package api.test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	UserPojo userPayload;

	public Logger logger;
	
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new UserPojo();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

	
	//logs
		
	logger = LogManager.getLogger(this.getClass());
		}


	@Test(priority=1)
	public void	testPostUser(){
		logger.info("***************Creating User**************");
				Response response =	UserEndPoints.createUser(userPayload);
		response.then().log().all();
		response.statusCode();
	}
	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info(" Reading User Info");
		
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		response.statusCode();

		Assert.assertEquals(response.getStatusCode(), 200);

	}


	@Test(priority=3)
	public void testUpdateUserByName() throws IOException {
		logger.info(" Updating User Info");
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());


		Response response =	UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		response.statusCode();

		//checking data after update
		logger.info(" Updated User Info");
		
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		responseAfterUpdate.statusCode();

		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);


	}


	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info(" Deleting User Info");
		logger.debug("Debugging");
		
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());

		logger.info(" User deleted Info");
		
		Assert.assertEquals(response.getStatusCode(), 200);

	}

}

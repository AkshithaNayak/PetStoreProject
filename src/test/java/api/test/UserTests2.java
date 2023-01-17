package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	User userPayload;
	
	//for logs
	public Logger logger;
				
	@BeforeClass
	public void setUp()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		//Logs
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("***************************Creating user ***************************************");
		Response response=UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("*************************** user created ***************************************");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("***************************getting user details ***************************************");
		Response response=UserEndpoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***************************Retrieved user details ***************************************");
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("***************************Updating user ***************************************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response=UserEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		//Checking data after update
		Response responseAfterUpdate=UserEndpoints2.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		
		Assert.assertEquals(responseAfterUpdate.statusCode(), 200);
		logger.info("*************************** user details updated ***************************************");
	}
	
	@Test(priority=3)
	public void testDeleteUser()
	{
		logger.info("***************************Deleting user ***************************************");
		Response response=UserEndpoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("*************************** user deleted ***************************************");
	}

}

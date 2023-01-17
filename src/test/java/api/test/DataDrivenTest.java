package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest 
{
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userId, String Username, String firstName, String lastName, String email, String passwrd, String ph)
	{
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(Username);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(passwrd);
		userPayload.setPhone(ph);
		
		Response response=UserEndpoints.createUser(userPayload);
		
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUser(String username)
	{
		Response response = UserEndpoints.deleteUser(username);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}

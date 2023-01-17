package api.endpoints;

public class Routes {
	
	/* 
	Swagger URI --> https://petstore.swagger.io

	Create user(Post) : https://petstore.swagger.io/v2/user
	Get user (Get): https://petstore.swagger.io/v2/user/{username}
	Update user (Put) : https://petstore.swagger.io/v2/user/{username}
	Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

	*/
	
	//For User module-endpoints
	public static String baseurl = "https://petstore.swagger.io/v2";
	
	public static String post_url = baseurl+"/user";
	public static String get_url = baseurl+"/user/{username}";
	public static String put_url = baseurl+"/user/{username}";
	public static String delete_url = baseurl+"/user/{username}";

	//For store api - endpoints
	
	//For pet api-endpoints
}

package api.endpoints;

// created to perform crud operations
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {

		//method created for getting URL's from properties file
	static ResourceBundle getURL() {

		ResourceBundle routes = ResourceBundle.getBundle("routes");// load properties file
		return routes;

	}

	public static Response createUser(UserPojo payload) {
		
String	post_url =	getURL().getString("post_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url);

		return response;

	}

	public static Response readUser(String userName) {
		
		
		String	get_url =	getURL().getString("get_url");
		Response response = given().pathParam(/* name */"username", userName/* value */)
				.when().get(get_url);

		return response;
	}

	public static Response updateUser(String userName, UserPojo payload) {
		
		String	update_url =	getURL().getString("update_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload).when().put(update_url);

		return response;

	}

	public static Response deleteUser(String userName) {

		String	delete_url =	getURL().getString("delete_url");
		Response response = given().pathParam("username", userName)
                            .when().delete(delete_url);

		return response;

	}

}

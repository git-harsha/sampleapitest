package testinfrastructure;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HTTPHelper {
	static final String apiKeyQueryParam = "api_key";
	static String apiKey;
	
	public static void setAPIKey(String key){
		apiKey = key;
	}
	
	public static void setBaseURI(String uri){
		RestAssured.baseURI = uri;
		RestAssured.basePath = "/3";
	}
	
	public static Response get(TMDBRequest request){
		Response response = given().
			queryParam(apiKeyQueryParam, apiKey).
			queryParams(RequestHelper.getQueryParams(request.getQueryParamsJson())).
			log().all().
			when().
			get(request.getApi().getPath());
		return response;
	}
}

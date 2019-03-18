package sampletest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import testinfrastructure.API;
import testinfrastructure.HTTPHelper;
import testinfrastructure.ResponseValidator;
import testinfrastructure.TMDBRequest;

public class SearchCompaniesTest {

	API api = API.SEARCH_COMPANIES;
	
	@Test(dataProvider="SampleData")
	public void test(String request, String expectedResponse) {
		Response actualResponse = HTTPHelper.get(new TMDBRequest(request, api));
		ResponseValidator responseValidator = new ResponseValidator(expectedResponse);
		responseValidator.validateResponseCode(actualResponse.getStatusCode());
		responseValidator.validateResponseBody(actualResponse.getBody().asString());
	}
	
	/**
	 * 1. I suggest that test data be stored in an 
	 *    easily maintainable place like in DB or Excel file
	 * 2. The application database (in this case the database of companies)
	 *    should be populated by test automation. That helps to fill in
	 *    expected results during test case creation. 
	 * 3. Various kind of test that can be run is documented at the bottom.      
	 */
	@DataProvider(name="SampleData")
	public Object[][] dp(){
		return new Object[][] {
			{//---------TEST-1-----------------------
			 //Verifies query param 'page' is optional	
				//_________REQUEST____________
				"{" + 
				"	\"query_parameters\": {" + 
				"		\"query\": \"Sony\"" +
				"	}" + 
				"}",
				
				//____EXPECTED-RESPONSE____
				"{ " + 
				"	\"statusCode\": 200" +
				"}"
			},
			{//---------TEST-2------------------------
			 //Verifies query param 'query' is required
				//_________REQUEST_____________
				"{" + 
				"	\"query_parameters\": {" + 
				"		\"pages\": \"1\"" + 
				"	}" + 
				"}",
				//_______EXPECTED-RESPONSE______
				"{ " + 
				"	\"statusCode\": 404" +
				"}"
			},
			{//---------TEST-3-------------------
			 //Shows how test shows failure result	
				//_________REQUEST______________
				"{" + 
				"	\"query_parameters\": {" + 
				"		\"query\": \"Sony\"," + 
				"		\"pages\": \"1\"" + 
				"	}" + 
				"}",
				//_______EXPECTED-RESPONSE_______
				"{ " + 
				"	\"statusCode\": 200," + 
				"	\"body\": " + 
				"{" + 
				"  \"page\": 1," + 
				"  \"results\": [" + 
				"    {" + 
				"      \"id\": 1," + 
				"      \"logo_path\": null," + 
				"      \"name\": \"Sony Classical\"" + 
				"    }," + 
				"    {" + 
				"      \"id\": 2," + 
				"      \"logo_path\": \"/xAu6FbLy58iFoBXArz9molnHS2v.png\"," + 
				"      \"name\": \"Sony Pictures\"" + 
				"    }," + 
				"    {" + 
				"      \"id\": 3," + 
				"      \"logo_path\": \"/GagSvqWlyPdkFHMfQ3pNq6ix9P.png\"," + 
				"      \"name\": \"Sony Pictures\"" + 
				"    }," + 
				"    {" + 
				"      \"id\": 4," + 
				"      \"logo_path\": null," + 
				"      \"name\": \"Sony U.S. Latin\"" + 
				"    }," + 
				"  ]," + 
				"  \"total_pages\": 1," + 
				"  \"total_results\": 4" + 
				"}" + 
				"}"
			}
			/**
			 * Following are the kinds of the tests that should be run:
			 * 1. Field level validations like,
			 *    (a) query is a space character
			 *    (b) query is a special character
			 *    (c) long String in query
			 *    (d) page is greater than total pages, and many more such tests
			 *    (e) large number value for page
			 * 2. Pagination tests to check if,
			 *    (a) each page has expected number of items
			 *    (b) it can correctly show correct number when page count is high (depends on data type used to store page count)
			 * 3. Search Results
			 *    (a) Does the number of search results match with items in backend/db
			 *    (b) Does wild cards search work (depends on if api support wild cards/regex)
			 *    (c) How does it display when backend data has special characters
			 */
		};
	}
}

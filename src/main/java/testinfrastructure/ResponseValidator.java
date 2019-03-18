package testinfrastructure;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ResponseValidator {
	private String expectedResponseBody;
	private int expectedStatusCode;

	public ResponseValidator(String json) {
		this.expectedResponseBody = setExpectedResponseBody(json);
		this.expectedStatusCode = setExpectedStatusCode(json);
	}

	public void validateResponseCode(int actualStatusCode) {
		Assert.assertEquals(actualStatusCode, expectedStatusCode,
				"Response code is: " + actualStatusCode + ". Response code should have been " + expectedStatusCode);
	}

	public void validateResponseBody(String actualResponseBody) {
		if (expectedResponseBody == null)
			return;
		try {
			JSONAssert.assertEquals(expectedResponseBody, actualResponseBody, JSONCompareMode.NON_EXTENSIBLE);
		} catch (JSONException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Expected response is custom json format to hold expected status code and
	 * expected response body
	 */
	private static String setExpectedResponseBody(String json) {
		Gson gson = new Gson();
		JsonObject customJson = gson.fromJson(json, JsonObject.class);
		JsonElement expectedResponseBody = customJson.get("body");
		if (expectedResponseBody == null)
			return null;
		else
			return expectedResponseBody.toString();
	}

	private static int setExpectedStatusCode(String json) {
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
		return jsonObject.get("statusCode").getAsInt();
	}
}
package testinfrastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RequestHelper {
	
	/** 
	 *  The request json is a custom format
	 *  to input query params into request
	 */
	private static final String queryParameters = "query_parameters";
	
	public static Map<String, String> getQueryParams(String json){
		Map<String, String> map = new HashMap<>();
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
		jsonObject = jsonObject.getAsJsonObject(queryParameters);
		for(Entry<String, JsonElement> entry : jsonObject.entrySet()) {
			map.put(entry.getKey(), entry.getValue().getAsString());
		}
		return map;
	}
}



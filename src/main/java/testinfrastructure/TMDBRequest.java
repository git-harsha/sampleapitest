package testinfrastructure;

import lombok.Data;

@Data
public class TMDBRequest {
	String queryParamsJson;
	API api;
	
	public TMDBRequest(String json, API api){
		this.queryParamsJson = json;
		this.api = api;
	}
}

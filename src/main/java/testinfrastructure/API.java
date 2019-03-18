package testinfrastructure;

public enum API {
	/** 
	 *  This enum hosts all API and their paths.
	 *  Here is sample of three API
	 */
	SEARCH_COMPANIES("/search/company"),
	SEARCH_COLLECTIONS("/search/collection"),
	SEARCH_KEYWORDS("/search/keyword");
	
	private final String path;
	
	API(String path){
		this.path = path;
	}
	
	String getPath() {
		return path;
	}
}

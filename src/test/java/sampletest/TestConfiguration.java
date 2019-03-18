package sampletest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import lombok.Data;
import testinfrastructure.HTTPHelper;

import org.apache.commons.lang3.exception.ExceptionUtils;

@Data
public class TestConfiguration {
	static final String configPropertyFile = "src/test/resources/config.properties";
	static final String baseURIProperty = "base_uri";
	static final String apiKeyProperty = "api_key";
	
	@BeforeSuite
	public static void setup() {
		Properties properties = new Properties();
		String apiKey = System.getProperty(apiKeyProperty);
		InputStream input = null;
		try {
			input = new FileInputStream(configPropertyFile);
			properties.load(input);
			HTTPHelper.setBaseURI(properties.getProperty(baseURIProperty));
			if (apiKey==null) {
				apiKey = properties.getProperty(apiKeyProperty);
				if (apiKey==null || apiKey.equals(""))
					Assert.fail("API key is missing.");
			}
			HTTPHelper.setAPIKey(apiKey);
		} catch (IOException e) {
			Assert.fail("Failed to load configuration properties. " + ExceptionUtils.getStackTrace(e));
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

package framework;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class GetProperties {
	private static Properties prop;
	private static String environment;
	static Logger log = LogConfig.getLoger(GetProperties.class);

 	public static String getProperty(String desiredValue) {
 		if (prop == null)
 			getEnvironment();
 		return prop.getProperty(desiredValue);
 	}

	private static void getEnvironment() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(getPropertiesPath()));
		} catch (Exception e) {
			log.error("Could not find file: " + getPropertiesPath());
			e.printStackTrace();
		}
		log.info("Selected environment: " + environment);
	}

	private static String getPropertiesPath() {
		if (environment == null)
			if (System.getProperty("environment") == null)
				environment = "si";
			else
				environment = System.getProperty("environment");
		return "src\\test\\resources\\" + environment + ".conf";
	}

	public static void setEnvironment(String env) {
		environment = env;
	}
}

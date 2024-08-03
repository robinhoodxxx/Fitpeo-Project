package utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigReader {
	
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    private static Properties properties = new Properties();

   

    public static String getProperty(String key) {
    	String path = "./src/test/resources/ConfigFiles/global.properties";
    	 try {
    		 InputStream input = new FileInputStream(path);
             properties.load(input);
             
         } catch (FileNotFoundException e) {
        	 logger.error("File Not Found "+path);
             e.printStackTrace();
         }
    	 catch(Exception e) {
    		 logger.error("Config Reader is failed "+e.getMessage());
             e.printStackTrace();
    	 }
    	
    	if(key==null || key.isBlank()) {
    		
    		logger.error(key + " is Empty or null");
    	}
    		
    		return properties.getProperty(key);
    	
    }
}


package api;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class baseClass {
	
	static Properties property;
	
	public static void loadProperty() throws IOException {
		property=new Properties();
		File fileName = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\value.properties");
		System.out.println(fileName.toString());
		FileReader readFile = new FileReader(fileName);
		property.load(readFile);
	}
	
	public static String getPropertyValue(String value) throws IOException {
		loadProperty();
		String data=property.getProperty(value);
		return data;
		
	}

}

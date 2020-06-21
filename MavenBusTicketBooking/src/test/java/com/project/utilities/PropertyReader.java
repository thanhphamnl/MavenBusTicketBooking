package com.project.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	public static FileInputStream inputStream = null;
	public static Properties prop = null;

	public static String ReadProperty(String PropertyName) throws IOException {
		String PropertyValue = null;
		String ProjectPath = System.getProperty("user.dir");
		inputStream = new FileInputStream(new File(ProjectPath + "/src/test/java/com/project/config/config.properties"));		
		prop = new Properties();
		prop.load(inputStream); // loading of the file is a prerequisite which allows to read from properties
								// file

		PropertyValue = prop.getProperty(PropertyName);
		return PropertyValue;
	}
}

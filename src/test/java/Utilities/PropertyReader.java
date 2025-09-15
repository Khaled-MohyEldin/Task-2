package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static String get(String propertyName) {
        return getPropertyValue("GlobalData.properties", propertyName);
    }

    private static String getPropertyValue(String filename, String propertyName) {
        String propertyValue;
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(filename)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return propertyValue;
    }
}
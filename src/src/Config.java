import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class Config {
    private static Properties properties = new Properties();

    static {
        try {
            InputStream input = Config.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static String getApiUrl() {
        return getProperty("api.base.url", "https://jsonplaceholder.typicode.com");
    }

    public static int getTimeout() {
        return Integer.parseInt(getProperty("http.timeout", "5000"));
    }
}
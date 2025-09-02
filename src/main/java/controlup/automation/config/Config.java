package controlup.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Config.class.getResourceAsStream("/base.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                throw new RuntimeException("base.properties not found under resources/config");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties", e);
        }
    }

    public static String get(String key) {
        return System.getProperty(key, props.getProperty(key));
    }

    public static String baseUrl() {
        return get("baseUrl");
    }

    public static String username() {
        return get("username");
    }

    public static String password() {
        return get("password");
    }
}

package net.serenitybdd.taxi.apis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by NoteP on 10.07.2017.
 */
public class APICredentials {
    private final String DEFAULT_CREDENTIALS_FILE = "tfl.properties";
    private static final String NOT_REGISTERED_MESSAGE = "To use this API register at https://api.tfl.gov.uk and create a file called tfl.properties in the root directory of your project";

    private final String appId;
    private final String appKey;

    public static APICredentials fromLocalEnvironment() {
        return new APICredentials();
    }

    public APICredentials() {
        try {
            Properties credentialsProperties = new Properties();
            credentialsProperties.load(new FileInputStream(DEFAULT_CREDENTIALS_FILE));
            appId = credentialsProperties.getProperty("app_id");
            appKey = credentialsProperties.getProperty("app_key");
        } catch (IOException e) {
            throw new NotRegisteredException(NOT_REGISTERED_MESSAGE);
        }

        if (isBlank(appId) || isBlank(appKey)) {
            throw new NotRegisteredException(NOT_REGISTERED_MESSAGE);
        }
    }

    public String getAppId() {
        return appId;
    }

    public String getAppKey() {
        return appKey;
    }
}

package configs;

import apiConstants.Constants;
import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:default.properties"
})
public interface TestConfig extends Config {
    @Key("baseUrl")
    @DefaultValue(Constants.BASE_URL)
    String getBaseUrl();

    @Key("authorization")
    String authorization();

    @Key("login")
    String login();

    @Key("password")
    String password();
}

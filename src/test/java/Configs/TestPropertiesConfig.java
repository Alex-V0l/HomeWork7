package Configs;

import Constants.Constants;
import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:default.properties"
})

public interface TestPropertiesConfig extends Config{
    @Key("baseUrl")
    @DefaultValue(Constants.BASE_URL)
    String getBaseURl();

    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();
}

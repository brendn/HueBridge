package dog.brendan.hue.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HueBridge {

//    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//    @JsonProperty("lights")
//    private Light[] lights;

    @JsonProperty("config")
    private Configuration config;

    public Configuration getConfig() {
        return config;
    }

}

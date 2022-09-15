package dog.brendan.hue.hue.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

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

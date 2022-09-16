package dog.brendan.hue.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dog.brendan.hue.api.model.HueBridge;
import dog.brendan.hue.api.model.Light;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HueService {

    @Value("${api.key}")
    private String key;

    @Value("${api.url}")
    private String url;

    private static final String REQUEST_ONOFF = "{\"on\":%s}";
    private static final String REQUEST_NAME = "{\"name\":\"%s\"}";

    private final RestTemplate template = new RestTemplate();

    public HueBridge getBridge() {
        return template.getForObject(url + key, HueBridge.class);
    }

    public List<Light> getLights() {
        List<Light> out = new ArrayList<>();
        ResponseEntity<String> rawJson = template.exchange(url + key + "/lights/", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});

        var json = JsonParser.parseString(rawJson.getBody()).getAsJsonObject();

        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            var light = new Gson().fromJson(entry.getValue(), Light.class);
            light.setID(entry.getKey());
            out.add(light);
        }
        return out;
    }

    public Optional<Light> getLight(int lightID) {
        var lights = getLights();
        for (Light light : lights) {
            if (Integer.parseInt(light.getId()) == lightID) {
                return Optional.of(light);
            }
        }
        return Optional.empty();
    }

    public void setLightState(int lightID, boolean on) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        template.put(url + key + "/lights/" + lightID + "/state", String.format(REQUEST_ONOFF, on));
    }

    public void setLightName(int lightID, String name) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        template.put(url + key + "/lights/" + lightID, String.format(REQUEST_NAME, name));
    }
}

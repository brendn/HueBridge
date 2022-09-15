package dog.brendan.hue.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dog.brendan.hue.api.model.HueBridge;
import dog.brendan.hue.api.model.Light;
import dog.brendan.hue.api.model.LightState;
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
            var light = entry.getValue().getAsJsonObject();
            var state = light.get("state").getAsJsonObject();
            var lightState = new LightState(state.get("on").getAsBoolean(), state.get("reachable").getAsBoolean(),
                    state.get("bri").getAsInt(), state.get("hue").getAsInt(), state.get("sat").getAsInt());
            var lightObj = new Light(entry.getKey(), light.get("name").toString(), light.get("type").toString());
            lightObj.setState(lightState);
            out.add(lightObj);
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
}

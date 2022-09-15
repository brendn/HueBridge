package dog.brendan.hue.hue;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dog.brendan.hue.hue.model.HueBridge;
import dog.brendan.hue.hue.model.Light;
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

@Service
public class HueService {

    @Value("${api.key}")
    private String key;

    @Value("${api.url}")
    private String url;

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
            var obj = entry.getValue().getAsJsonObject();
            out.add(new Light(entry.getKey(), obj.get("name").toString(), obj.get("type").toString()));
        }
        return out;
    }

    public void setLightState(int lightID, boolean on) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        template.put(url + key + "/lights/" + lightID + "/state", String.format("{\"on\":%s}", on));
    }
}

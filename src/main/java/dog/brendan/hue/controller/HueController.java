package dog.brendan.hue.controller;

import dog.brendan.hue.api.HueService;
import dog.brendan.hue.api.model.Light;
import dog.brendan.hue.exception.LightNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HueController {

    @Autowired
    private HueService service;

    @RequestMapping("/")
    public String index(Model model) {
        var bridge = service.getBridge();
        var lightList = service.getLights();
        model.addAttribute("lights", lightList);
        model.addAttribute("bridge", bridge);
        return "home";
    }

    @RequestMapping("/toggle/{id}")
    private String toggleLight(@PathVariable("id") String id) {
        Light light = service.getLight(Integer.parseInt(id)).orElseThrow(LightNotFoundException::new);
        service.setLightState(Integer.parseInt(id), !light.getState().isOn());
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    private String editLight(@PathVariable("id") String id, Model model) {
        Light light = service.getLight(Integer.parseInt(id)).orElseThrow(LightNotFoundException::new);
        model.addAttribute("light", light);
        return "edit";
    }

    @PostMapping("/edit")
    private String pushEdits(@RequestParam String id, @RequestParam String name) {
        //TODO setLightName
        service.setLightName(Integer.parseInt(id), name);
        return "redirect:/";
    }
}

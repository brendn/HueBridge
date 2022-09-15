package dog.brendan.hue.controller;

import dog.brendan.hue.hue.HueService;
import dog.brendan.hue.hue.model.HueBridge;
import dog.brendan.hue.hue.model.Light;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

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
        service.setLightState(Integer.parseInt(id), true);
        return "redirect:/";
    }
}

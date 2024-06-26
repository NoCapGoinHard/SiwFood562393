package it.uniroma3.siwfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacadeController {

    @GetMapping("") //in realtà andrebbe in una classe facade controller
    public String getIndexPage() {
        return "index.html";
    }
}

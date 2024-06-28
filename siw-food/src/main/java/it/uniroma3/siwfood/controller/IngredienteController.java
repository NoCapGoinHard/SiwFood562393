package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siwfood.service.AllergeneService;
import it.uniroma3.siwfood.service.IngredienteService;

@Controller
public class IngredienteController {
    
    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private AllergeneService allergeneService;
    
    @GetMapping("/ingredienti")
    public String ingredienti(Model model) {
        model.addAttribute("ingredienti", this.ingredienteService.findAll());
        return "ingredienti.html";
    }

     

}

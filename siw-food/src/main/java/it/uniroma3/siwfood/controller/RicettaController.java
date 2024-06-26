package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.repository.RicettaRepository;
import it.uniroma3.siwfood.service.RicettaService;

@Controller
public class RicettaController {
    
    @Autowired
    private RicettaService ricettaService;
    
    /*
    @Autowired
    private RicettaRepository ricettaRepository;
    */

    @GetMapping("/ricette")
    public String getRicette(Model model) {
        model.addAttribute("ricette", this.ricettaService.findAll());
        return "ricette.html";
    }

    @GetMapping("/ricetta/{id}")
    public String getRicetta(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ricetta", this.ricettaService.findById(id));
        return "ricetta.html";
    }

    @GetMapping("/formNuovaRicetta")
    public String formNuovaRicetta(Model model) {
        model.addAttribute("ricetta", new Ricetta());
        return "formNuovaRicetta.html";
    }

    @PostMapping("/ricetta/aggiunta")
    public String formNuovaRicetta(@ModelAttribute("ricetta") Ricetta ricetta, Model model) {
        this.ricettaService.save(ricetta);
        model.addAttribute("ricetta", ricetta);
        return "redirect:/ricette";
    }

    @GetMapping("/ricette/cercaByNome")
    public String getFormCercaRicette() {
        return "formCercaRicette.html";
    }

    @PostMapping("/ricette/trovate")
    public String getRicetteByNomeAndCognome(@RequestParam String nome, Model model) {
        model.addAttribute("ricette", this.ricettaService.findAllByNome(nome));
        return "ricette.html";
    }



}

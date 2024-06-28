package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siwfood.model.Ingrediente;
import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.service.AllergeneService;
import it.uniroma3.siwfood.service.IngredienteService;
import it.uniroma3.siwfood.service.RicettaService;

@Controller
public class RicettaController {
    
    @Autowired
    private RicettaService ricettaService;
    

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private AllergeneService allergeneService;
    

    @GetMapping("/ricette")
    public String getRicette(Model model) {
        model.addAttribute("ricette", this.ricettaService.findAll());
        return "ricette.html";
    }
    
    @GetMapping("/ricetta/{id}")
    public String getRicetta(@PathVariable("id") Long id, Model model) {
        Ricetta ricetta = this.ricettaService.findById(id);
        model.addAttribute("ricetta", ricetta);
        return "ricetta";
    }

    @GetMapping("/forms/formNuovaRicetta")
    public String formNuovaRicetta(Model model) {
        model.addAttribute("ricetta", new Ricetta());
        return "forms/formNuovaRicetta.html";
    }

    @PostMapping("/ricetta/aggiunta")
    public String formNuovaRicetta(@ModelAttribute("ricetta") Ricetta ricetta, Model model) {
        this.ricettaService.save(ricetta);
        model.addAttribute("ricetta", ricetta);
        return "redirect:/ricette";
    }
    
    @GetMapping("/ricetta/{id}/modifica")
    public String formModificaRicetta(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ricetta", this.ricettaService.findById(id));
        return "forms/formModificaRicetta.html";
    }

    @GetMapping("/ricetta/{id}/modifica/aggiungiIngrediente")
    public String formAggiungiIngrediente(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ricetta", this.ricettaService.findById(id));
        model.addAttribute("ingrediente", new Ingrediente());
        return "forms/formAggiungiIngrediente.html";
    }

    @PostMapping("/ricetta/{id}/modifica/aggiungiIngrediente")
    public String formAggiungiIngrediente(@PathVariable("id") Long id, @ModelAttribute Ingrediente ingrediente) {
        Ricetta ricetta = this.ricettaService.findById(id);
        ingrediente.setRicetta(ricetta);
        ingredienteService.save(ingrediente);
        return "redirect:/ricetta/" + ricetta.getId();
    }

    @PostMapping("/ricetta/{id}/aggiorna")
    public String aggiornaRicetta(@PathVariable("id") Long id, @ModelAttribute Ricetta ricetta) {
        ricetta.setId(id);
        this.ricettaService.save(ricetta);
        return "redirect:/ricetta/" + ricetta.getId();
    }

    @GetMapping("/ricette/cercaByNome")
    public String getFormCercaRicette() {
        return "forms/formCercaRicette.html";
    }

    @PostMapping("/ricette/trovate")
    public String getRicetteByNome(@RequestParam String nome, Model model) {
        model.addAttribute("ricette", this.ricettaService.findAllByNome(nome));
        return "ricette.html";
    }

    @GetMapping("ricetta/{id}/elimina")
    public String eliminaRicettaById(@PathVariable("id") Long id) {
        this.ricettaService.deleteById(id);
        return "redirect:/ricette";
    }

}

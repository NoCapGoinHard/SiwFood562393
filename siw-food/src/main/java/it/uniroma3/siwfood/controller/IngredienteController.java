package it.uniroma3.siwfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siwfood.model.Allergene;
import it.uniroma3.siwfood.model.Ingrediente;
import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.service.AllergeneService;
import it.uniroma3.siwfood.service.IngredienteService;
import it.uniroma3.siwfood.service.RicettaService;

@Controller
public class IngredienteController {
    
    @Autowired
    private RicettaService ricettaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private AllergeneService allergeneService;
    


    @GetMapping("/ingredienti")
    public String ingredienti(Model model) {
        model.addAttribute("ingredienti", this.ingredienteService.findDistinctNomi());
        return "ingredienti.html";
    }

    @GetMapping("/ingrediente/{nome}/aggiungiAllergene")
    public String formAggiungiAllergene(@PathVariable("nome") String nome, Model model) {
        model.addAttribute("ingredienti", this.ingredienteService.findAllByNome(nome));
        model.addAttribute("allergene", new Allergene());
        return "forms/formAggiungiAllergene.html";
    }

    @PostMapping("/ingrediente/{nome}/aggiungiAllergene")
    public String formAggiungiAllergene(@PathVariable("nome") String nome, @ModelAttribute("allergene") Allergene allergene, Model model) {
        allergeneService.save(allergene);
        List<Ingrediente> ingredienti = this.ingredienteService.findAllByNome(nome);
        for(Ingrediente i: ingredienti) {
                i.addAllergene(allergene);
                ingredienteService.save(i);
        }
        model.addAttribute("ingredienti", ingredienti);
        model.addAttribute("allergene", allergene);
        return "redirect:/ingrediente/" + nome;

        
    }
}

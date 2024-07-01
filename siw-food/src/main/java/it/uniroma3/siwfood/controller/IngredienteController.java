package it.uniroma3.siwfood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siwfood.model.Allergene;
import it.uniroma3.siwfood.model.Ingrediente;
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

    @GetMapping("/ingredienti/cercaIngrediente")
    public String cercaIngrediente() {
        return "forms/formCercaIngrediente.html";
    }
    @PostMapping("/ingrediente") 
    public String getIngredienteByNome(@RequestParam("nome") String nome, Model model){
        List<Ingrediente> ingredienti = this.ingredienteService.findAllByNome(nome);
        if(!ingredienti.isEmpty()) {
            model.addAttribute("ingrediente", ingredienti.get(0));
            return "ingrediente.html";
        }
        else {
            model.addAttribute("messaggioErrore", "Non ci sono ingredienti con questo nome");
            return "forms/formCercaIngrediente.html";
        }
    }

    @GetMapping("/ingrediente/{nome}")
    public String ingrediente(@PathVariable("nome") String nome, Model model) {
        List<Ingrediente> ingredienti = this.ingredienteService.findAllByNome(nome);
        model.addAttribute("ingrediente", ingredienti.get(0));
        return "redirect:/ingrediente/"+ nome;
    }

    @GetMapping("/ingrediente/{nome}/elimina")
    public String eliminaIngredienteByNome(@PathVariable("nome") String nome) {
        this.ingredienteService.deleteAllByNome(nome);
        return "redirect:/ingredienti";
    }
    
    @GetMapping("/ingrediente/{nome}/aggiungiAllergene")
    public String formAggiungiAllergene(@PathVariable("nome") String nome, Model model) {
        model.addAttribute("ingredienti", this.ingredienteService.findAllByNome(nome));
        model.addAttribute("allergene", new Allergene());
        return "forms/formAggiungiAllergene.html";
    }

    @PostMapping("/ingrediente/{nome}/aggiungiAllergene")
    public String formAggiungiAllergene(@PathVariable("nome") String nome, @ModelAttribute("allergene") Allergene allergene, Model model) {
        List<Ingrediente> ingredienti = this.ingredienteService.findAllByNome(nome);
        for(Ingrediente i: ingredienti) {
            allergene.addIngredienteCoinvolto(i);
        }
        allergeneService.save(allergene);
        for(Ingrediente i: ingredienti) {
                i.addAllergene(allergene);
                ingredienteService.save(i);
        }
        model.addAttribute("ingredienti", ingredienti);
        model.addAttribute("allergene", allergene);
        return "/ingrediente/"+nome;

    }

    @GetMapping("ingrediente/{nome}/eliminaAllergene/{idAllergene}")
    public String getMethodName(@PathVariable("nome") String nome, @PathVariable("idAllergene") Long idAllergene) {
        Allergene allergene = this.allergeneService.findById(idAllergene);
        List<Ingrediente> ingredienti = this.ingredienteService.findAllByNome(nome);

        for(Ingrediente i: ingredienti) {
            i.removeAllergene(allergene);
            this.ingredienteService.save(i);
        }
        
        this.allergeneService.deleteById(idAllergene);
       
        return "ingredienti.html";
    }
    



}

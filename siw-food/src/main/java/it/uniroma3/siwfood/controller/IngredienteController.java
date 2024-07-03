package it.uniroma3.siwfood.controller;

import java.util.ArrayList;
import java.util.List;

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
        List<Ingrediente> ingredienti = this.ingredienteService.findDistinctNomi();
        model.addAttribute("ingredienti", ingredienti);
        return "ingredienti.html";
    }

    @GetMapping("/ingredienti/{ingrediente_nome}")
    public String ingrediente(@PathVariable("ingrediente_nome") String nome, Model model) {
        List<Ingrediente> ingredienti = this.ingredienteService.findAllByNome(nome);
        model.addAttribute("ingrediente", ingredienti.get(0));
        return "ingrediente.html";
    }

    @GetMapping("/ingredienti/search")
    public String cercaIngrediente() {
        return "forms/formCercaIngrediente.html";
    }
    @PostMapping("/ingredienti/byNome") 
    public String getIngredientiByNome(@RequestParam("nome") String nome, Model model){
        List<Ingrediente> ingredienti = this.ingredienteService.findAllByNome(nome);
        if(!ingredienti.isEmpty()) {
            model.addAttribute("ingrediente", ingredienti.get(0));
            return "ingredienti.html";
        }
        else {
            model.addAttribute("messaggioErrore", "Non ci sono ingredienti con questo nome");
            return "forms/formCercaIngrediente.html";
        }
    }

    @GetMapping("/admin/deleteIngrediente/{ingrediente_nome}")
    public String eliminaIngredienteByNome(@PathVariable("ingrediente_nome") String nome) {
        this.ingredienteService.deleteAllByNome(nome);
        return "redirect:/ingredienti";
    }
    
    @GetMapping("/admin/aggiungiAllergene/{ingrediente_nome}")
    public String formAggiungiAllergene(@PathVariable("ingrediente_nome") String nome, Model model) {
        model.addAttribute("ingredienti", this.ingredienteService.findAllByNome(nome));
        model.addAttribute("allergene", new Allergene());
        return "forms/formAggiungiAllergene.html";
    }

    @PostMapping("/admin/aggiungiAllergene/{ingrediente_nome}")
    public String formAggiungiAllergene(@PathVariable("ingrediente_nome") String nome, @ModelAttribute("allergene") Allergene allergene, Model model) {
        List<Ingrediente> ingredienti = this.ingredienteService.findAllByNome(nome);
        this.allergeneService.save(allergene);
        
        for(Ingrediente i: ingredienti) {
            allergene.addIngredienteCoinvolto(i);
            i.addAllergene(allergene);
            this.ingredienteService.save(i);
        }

        model.addAttribute("ingredienti", ingredienti);
        model.addAttribute("allergene", allergene);
        return "redirect:/ingredienti/"+nome;

    }

    @GetMapping("admin/eliminaAllergene/{ingrediente_nome}/{id_Allergene}")
    public String getMethodName(@PathVariable("ingrediente_nome") String nome, @PathVariable("id_Allergene") Long idAllergene) {
        Allergene allergene = this.allergeneService.findById(idAllergene);
        List<Ingrediente> ingredienti = this.ingredienteService.findAllByNome(nome);

        for(Ingrediente i: ingredienti) {
            i.removeAllergene(allergene);
            this.ingredienteService.save(i);
        }
        
        this.allergeneService.deleteById(idAllergene);
       
        return "redirect:/ingredienti/" + nome;
    }


}

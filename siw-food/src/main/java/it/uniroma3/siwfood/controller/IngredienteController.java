package it.uniroma3.siwfood.controller;

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
import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Ingrediente;
import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.service.AllergeneService;
import it.uniroma3.siwfood.service.IngredienteService;
import it.uniroma3.siwfood.service.RicettaService;
import it.uniroma3.siwfood.service.auth.UserService;

@Controller
public class IngredienteController {
    
    @Autowired
    private RicettaService ricettaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private AllergeneService allergeneService;

    @Autowired
    private UserService userService;
    


    @GetMapping("/admin/ingredienti")
    public String ingredienti(Model model) {
        List<Ingrediente> ingredienti = this.ingredienteService.findDistinctNomi();
        model.addAttribute("ingredienti", ingredienti);
        return "ingredienti.html";
    }

    @GetMapping("/admin/ingredienti/{ingrediente_nome}")
    public String ingrediente(@PathVariable("ingrediente_nome") String nome, Model model) {
        List<Ingrediente> ingredienti = this.ingredienteService.findAllByNome(nome);
        model.addAttribute("ingrediente", ingredienti.get(0));
        return "ingrediente.html";
    }

    @GetMapping("/admin/ingredienti/search")
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
            return "forms/formCercaIngredienteSoloAdmin.html";
        }
    }



    @GetMapping("cuoco/rimuoviIngrediente{ingrediente_id}/{user_id}")
    public String removeIngredienteCuoco(@PathVariable("ingrediente_id") Long ingrediente_id, @PathVariable("user_id") Long user_id, Model model) {
        Ingrediente ingrediente = this.ingredienteService.findById(ingrediente_id);
        Ricetta ricetta = ingrediente.getRicetta();
        Cuoco cuoco = ricetta.getCuoco();
        if(this.userService.findById(user_id).getCuoco().equals(cuoco)) {
            ricetta.removeIngrediente(ingrediente);
            this.ingredienteService.deleteById(ingrediente_id);
            this.ricettaService.save(ricetta);
            return "redirect:/ricette/" + ingrediente.getRicetta().getId();
        }
        else {
            model.addAttribute("messaggioErrore", "Non disponi delle autorizzazioni per questa operazione!");
            return "redirect:/ricette/" + ingrediente.getRicetta().getId();
        }
    }

    @GetMapping("/admin/rimuoviIngrediente/{ingrediente_id}")
    public String removeIngredienteAdmin(@PathVariable("ricetta_id") Long ricetta_id, @PathVariable("ingrediente_id") Long ingrediente_id, Model model) {
        Ingrediente ingrediente = this.ingredienteService.findById(ingrediente_id);
        Ricetta ricetta = ingrediente.getRicetta();
        ricetta.removeIngrediente(ingrediente);
        this.ingredienteService.deleteById(ingrediente_id);
        this.ricettaService.save(ricetta);
        return "redirect:/ricette/" + ricetta_id;
    }



    @GetMapping("/cuoco/aggiungiIngrediente/{ricetta_id}/{user_id}")
    public String formAggiungiIngredienteCuoco(@PathVariable("ricetta_id") Long ricetta_id, @PathVariable("user_id") Long user_id, Model model) {
        Ricetta ricetta = this.ricettaService.findById(ricetta_id);
        Cuoco cuoco = ricetta.getCuoco();
        if(this.userService.findById(user_id).getCuoco().equals(cuoco)) {
            model.addAttribute("ingrediente", new Ingrediente());
            return "forms/formAggiungiIngredienteDalCuoco.html";
        }
        else {
            model.addAttribute("messaggioErrore", "Non disponi delle autorizzazioni per questa operazione!");
            return "redirect:/ricette/" + ricetta_id;
        }
    }
    @PostMapping("cuoco/aggiungiIngrediente/{ricetta_id}")
    public String aggiungiIngredienteCuoco(@PathVariable("ricetta_id") Long ricetta_id, @ModelAttribute Ingrediente ingrediente) {
        ingrediente.setRicetta(this.ricettaService.findById(ricetta_id));
        this.ricettaService.findById(ricetta_id).addIngrediente(ingrediente);
        this.ingredienteService.save(ingrediente);
        return "redirect:/ricette/" + ingrediente.getRicetta().getId();
    }



    @GetMapping("/admin/aggiungiIngrediente/{ricetta_id}")
    public String formAggiungiIngredienteCuoco(@PathVariable("ricetta_id") Long ricetta_id, Model model) {
        model.addAttribute("ingrediente", new Ingrediente());
        return "forms/formAggiungiIngredienteAdmin.html";
    }
    @PostMapping("admin/aggiungiIngrediente/{ricetta_id}")
    public String aggiungiIngredienteAdmin(@PathVariable("ricetta_id") Long ricetta_id, @ModelAttribute Ingrediente ingrediente) {
        ingrediente.setRicetta(this.ricettaService.findById(ricetta_id));
        this.ricettaService.findById(ricetta_id).addIngrediente(ingrediente);
        this.ingredienteService.save(ingrediente);
        return "redirect:/ricette/" + ingrediente.getRicetta().getId();
    }



    @GetMapping("/cuoco/editIngrediente/{ingrediente_id}/{user_id}")
    public String formModificaIngredienteDalCuoco(@PathVariable("ingrediente_id") Long ingrediente_id, @PathVariable("user_id") Long user_id, Model model) {
        Ingrediente ingrediente = this.ingredienteService.findById(ingrediente_id);
        Ricetta ricetta = ingrediente.getRicetta();
        Cuoco cuoco = ricetta.getCuoco();
        if(this.userService.findById(user_id).getCuoco().equals(cuoco)) {
            return "forms/formModificaIngredienteDalCuoco.html";
        }
        else{
            model.addAttribute("messaggioErrore", "Non disponi delle autorizzazioni per questa operazione!");
            return "redirect:/ricette/" + ricetta.getId();
        }
    }
    @PostMapping("/cuoco/editIngrediente/{ingrediente_id}")
    public String editIngredienteDalCuoco(@PathVariable("ingrediente_id") Long ingrediente_id, @ModelAttribute Ingrediente ingrediente) {
        ingrediente.setId(ingrediente_id);
        this.ingredienteService.save(ingrediente);
        return "redirect:/ricetta/" + ingrediente.getRicetta().getId();
    }



    @GetMapping("admin/editIngrediente/{ingrediente_id}")
    public String formModificaIngredienteAdmin(@PathVariable("ingrediente_id") Long ingrediente_id, Model model) {
        model.addAttribute("ingrediente", this.ingredienteService.findById(ingrediente_id));
        return "forms/formModificaIngredienteAdmin.html";
    }
    @PostMapping("admin/editIngrediente/{ingrediente_id}")
    public String editIngredienteAdmin(@PathVariable("ingrediente_id") Long ingrediente_id, @ModelAttribute Ingrediente ingrediente) {
        ingrediente.setId(ingrediente_id);
        this.ingredienteService.save(ingrediente);
        return "redirect:/ricette" + ingrediente.getRicetta().getId();
    }


    //DAL SISTEMA
    @GetMapping("/admin/deleteIngrediente/{ingrediente_nome}")
    public String eliminaIngredienteByNome(@PathVariable("ingrediente_nome") String nome) {
        this.ingredienteService.deleteAllByNome(nome);
        return "redirect:/ingredienti";
    }


    @GetMapping("/admin/aggiungiAllergene/{ingrediente_nome}")
    public String formAggiungiAllergene(@PathVariable("ingrediente_nome") String nome, Model model) {
        model.addAttribute("ingredienti", this.ingredienteService.findAllByNome(nome));
        model.addAttribute("allergene", new Allergene());
        return "forms/formAggiungiAllergeneSoloAdmin.html";
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



    //DAL SISTEMA
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

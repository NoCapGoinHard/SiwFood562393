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
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.IngredienteService;
import it.uniroma3.siwfood.service.RicettaService;
import it.uniroma3.siwfood.service.auth.UserService;



@Controller
public class RicettaController {
    
    @Autowired
    private RicettaService ricettaService;

    @Autowired
    private CuocoService cuocoService;

    @Autowired
    private UserService userService;

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/ricette")   
    public String getRicette(Model model) {
        model.addAttribute("ricette", this.ricettaService.findAll());
        return "ricette.html";
    }
    
    @GetMapping("/ricette/{ricetta_id}")
    public String getRicetta(@PathVariable("ricetta_id") Long id, Model model) {
        model.addAttribute("ricetta", this.ricettaService.findById(id));
        return "ricetta.html";
    }

    @GetMapping("/ricette/search")
    public String getFormSearchRicetta() {
        return "forms/formCercaRicette.html";
    }
  
    @PostMapping("/ricette/byNome")
    public String postRicetteByNome(@RequestParam String nome, Model model) {
        model.addAttribute("ricette", this.ricettaService.findAllByNome(nome));
        return "ricette.html";
    }

    //@PostMapping("/ricette/byIngrediente")
    //public String postRicetteByIngrediente(@RequestParam String ingr, Model model) {
    //    model.addAttribute("ricette", this.ricettaService.findByIngredienteNome(ingr));
    //    return "ricette.html";
    //}




    @GetMapping("/admin/editRicetta/{ricetta_id}")
    public String getFormEditRicetta(@PathVariable("ricetta_id") Long id, Model model) {
        model.addAttribute("ricetta", this.ricettaService.findById(id));
        return "forms/formEditRicetta.html";
    }

    @PostMapping("/admin/editRicetta/{ricetta_id}")
    public String updateRicetta(@PathVariable("ricetta_id") Long id, @ModelAttribute Ricetta ricetta) {
        ricetta.setId(id);
        this.ricettaService.save(ricetta);
        return "redirect:/ricette/" + ricetta.getId();
    }

    @GetMapping("/admin/deleteRicetta/{ricetta_id}")
    public String deleteRicetteById(@PathVariable("ricetta_id") Long id) {
        this.ricettaService.deleteById(id);
        return "redirect:/ricette";
    }

    @GetMapping("/ricette/aggiungiIngrediente/{ricetta_id}")


}

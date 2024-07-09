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

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Ingrediente;
import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.model.auth.User;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.RicettaService;



@Controller
public class RicettaController extends GlobalController {
    
    @Autowired
    private RicettaService ricettaService;

    @Autowired
    private CuocoService cuocoService;


    @GetMapping("/ricette")   
    public String ricette(Model model) {
        model.addAttribute("ricette", this.ricettaService.findAll());
        return "ricette.html";
    }
    


    @GetMapping("/ricette/{ricetta_id}")
    public String ricetta(@PathVariable("ricetta_id") Long id, Model model) {
        model.addAttribute("ricetta", this.ricettaService.findById(id));
        return "ricetta.html";
    }



    @GetMapping("/ricette/search")
    public String search() {
        return "forms/formCercaRicette.html";
    }
    @PostMapping("/ricette/byNome")
    public String byNome(@RequestParam String nome, Model model) {
        model.addAttribute("ricette", this.ricettaService.findAllByNome(nome));
        return "ricette.html";
    }

///VECCHIO TENTATIVO DI MODIFICA E AGGIUNTA INGREDIENTE

//    @GetMapping("/admin/editRicetta/{ricetta_id}")
//    public String editRicettaAdmin(@PathVariable("ricetta_id") Long id, Model model) {
//        User user = getCredentials().getUser();
//        Cuoco cuoco = this.ricettaService.findById(id).getCuoco();
//        if (getCredentials().isAdmin()
//        || cuocoService.findByNomeAndCognome(user.getNome(), user.getCognome()).getId() == cuoco.getId()) {
//            model.addAttribute("ricetta", this.ricettaService.findById(id));
//            return "forms/formModificaRicettaAdmin.html";
//        }
//        else {
//            model.addAttribute("messaggioErrore", "Non disponi per le autorizzazioni necessarie per questa operazione!");
//            return "redirect:/ricette/" + id;
//        }
//    }
//    @PostMapping("/admin/editRicetta/{ricetta_id}")
//    public String editRicettaAdmin(@PathVariable("ricetta_id") Long id, @ModelAttribute Ricetta ricetta, @ModelAttribute List<Ingrediente> ingredienti) {
//        ricetta.setId(id);
//        this.ricettaService.save(ricetta);
//        return "redirect:/ricette/" + ricetta.getId();
//    }



    //NUOVO TENTATIVO DI EDIT RICETTA E INGREDIENTE
    @GetMapping("/admin/editRicetta/{ricetta_id}")
    public String getFormEditRicetta(@PathVariable("ricetta_id") Long id, Model model) {
        Ricetta ricetta = this.ricettaService.findById(id);
        User user = getCredentials().getUser();
        if ((getCredentials().isAdmin()
        || cuocoService.findByNomeAndCognome(user.getNome(), user.getCognome()).getId() == ricetta.getCuoco().getId())) {
            model.addAttribute("ricetta", ricetta);
            model.addAttribute("ingredienti", this.ricettaService.findById(id).getIngredienti());
            model.addAttribute("nuovoIngrediente", new Ingrediente());
            return "forms/formModificaRicettaAdmin.html";
        }
        return "redirect:/ricette/" + id;
    }

    @PostMapping("/admin/editRicetta/{id}")
    public String updateRicetta(@PathVariable("id") Long id, @ModelAttribute Ricetta ricetta, @ModelAttribute List<Ingrediente> ingredienti) {
        ricetta.setId(id);
        this.ricettaService.save(ricetta);
        return "redirect:/ricette/" + ricetta.getId();
    }



    //DAL SISTEMA
    @GetMapping("/admin/deleteRicetta/{ricetta_id}")
    public String deleteRicettaAdmin(@PathVariable("ricetta_id") Long id, Model model) {
        User user = getCredentials().getUser();
        Cuoco cuoco = this.ricettaService.findById(id).getCuoco();
        if (getCredentials().isAdmin()
        || cuocoService.findByNomeAndCognome(user.getNome(), user.getCognome()).getId() == cuoco.getId()) {
            this.ricettaService.deleteById(id);
            return "redirect:/ricette";
        }
        else {
            model.addAttribute("messaggioErrore", "Non disponi per le autorizzazioni necessarie per questa operazione!");
            return "redirect:/ricette/" + id;
        }
    }



    @GetMapping("/admin/addRicetta/{cuoco_id}")
    public String formNuovaRicettaAdmin(@PathVariable("cuoco_id") Long id,Model model) {
        User user = getCredentials().getUser();
        if (getCredentials().isAdmin()
        || cuocoService.findByNomeAndCognome(user.getNome(), user.getCognome()).getId() == id) {
            model.addAttribute("cuoco", this.cuocoService.findById(id));
            model.addAttribute("ricetta", new Ricetta());
            return "forms/formNuovaRicettaAdmin.html";
        }
        else {
            model.addAttribute("messaggioErrore", "Non disponi per le autorizzazioni necessarie per questa operazione!");
            return "redirect:/cuochi/" + id;
        }
    }
    @PostMapping("/admin/addRicetta/{cuoco_id}")
    public String addRicettaAdmin(@PathVariable("cuoco_id") Long id, @ModelAttribute Ricetta ricetta) {
        ricetta.setCuoco(this.cuocoService.findById(id));
        this.ricettaService.save(ricetta);
        return "redirect:/cuochi/" + id;
    }

}

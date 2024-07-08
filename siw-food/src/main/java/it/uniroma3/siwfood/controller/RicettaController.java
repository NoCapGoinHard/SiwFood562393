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



//    @GetMapping("/cuoco/editRicetta/{ricetta_id}/{user_id}")
//    public String formModificaRicettaDalCuoco(@PathVariable("ricetta_id") Long ricetta_id, @PathVariable("user_id") Long user_id, Model model) {
//        Cuoco cuoco = this.userService.findById(user_id).getCuoco();
//        Ricetta ricetta = this.ricettaService.findById(ricetta_id);
//        if(ricetta.getCuoco().equals(cuoco)) {
//            model.addAttribute("ricetta", ricetta);
//            return "forms/formModificaRicettaDalCuoco.html";
//        }
//        else return "redirect:/ricette" + ricetta_id;
//    }
//    @PostMapping("/cuoco/editRicetta/{ricetta_id}")
//    public String editRicettaCuoco(@PathVariable("ricetta_id") Long id, @ModelAttribute Ricetta ricetta) {
//        ricetta.setId(id);
//        this.ricettaService.save(ricetta);
//        return "redirect:/ricette/" + id;
//    }



    @GetMapping("/admin/editRicetta/{ricetta_id}")
    public String editRicettaAdmin(@PathVariable("ricetta_id") Long id, Model model) {
        User user = getCredentials().getUser();
        if (getCredentials().isAdmin()
        && cuocoService.findByNomeAndCognome(user.getNome(), user.getCognome()).getId() != id) {
            model.addAttribute("ricetta", this.ricettaService.findById(id));
            return "forms/formModificaRicettaAdmin.html";
        }
        else {
            model.addAttribute("messaggioErrore", "Non disponi per le autorizzazioni necessarie per questa operazione!");
            return "messaggioErrore";
        }
    }
    @PostMapping("/admin/editRicetta/{ricetta_id}")
    public String editRicettaAdmin(@PathVariable("ricetta_id") Long id, @ModelAttribute Ricetta ricetta) {
        ricetta.setId(id);
        this.ricettaService.save(ricetta);
        return "redirect:/ricette/" + ricetta.getId();
    }



//    @GetMapping("cuoco/deleteRicetta/{ricetta_id}/{user_id}")
//    public String deleteRicettaCuoco(@PathVariable("ricetta_id") Long ricetta_id, @PathVariable("user_id") Long user_id) {
//        Ricetta ricetta = this.ricettaService.findById(ricetta_id);
//        Cuoco cuoco = this.userService.findById(user_id).getCuoco();
//        if(ricetta.getCuoco().equals(cuoco)) {
//            this.ricettaService.deleteById(ricetta_id);
//            return "redirect:/ricette";
//        }
//        else return "redirect:/ricette/" + ricetta_id;
//    }


    //DAL SISTEMA
    @GetMapping("/admin/deleteRicetta/{ricetta_id}")
    public String deleteRicettaAdmin(@PathVariable("ricetta_id") Long id) {
        User user = getCredentials().getUser();
        if (getCredentials().isAdmin()
        && cuocoService.findByNomeAndCognome(user.getNome(), user.getCognome()).getId() != id) {
            this.ricettaService.deleteById(id);
            return "redirect:/ricette";
        }
        else {
            return "redirect:/error";
        }
    }



//    @GetMapping("/cuoco/addRicetta/{cuoco_id}/{user_id}")
//    public String formNuovaRicettaCuoco(@PathVariable("cuoco_id") Long idC, @PathVariable("user_id") Long idU,Model model) {
//        
//        if(!(this.cuocoService.findById(idC).equals(this.userService.findById(idU).getCuoco()))){
//            return "redirect:/cuochi/" + idC;
//        }
//        
//        model.addAttribute("cuoco", this.cuocoService.findById(idC));
//        model.addAttribute("ricetta", new Ricetta());
//        return "forms/formNuovaRicettaDalCuoco.html";
//    }
//    @PostMapping("/cuochi/addRicetta/{cuoco_id}")
//    public String addRicettaCuoco(@PathVariable("cuoco_id") Long cuocoId, @ModelAttribute Ricetta ricetta) {
//        ricetta.setCuoco(this.cuocoService.findById(cuocoId));
//        this.ricettaService.save(ricetta);
//        return "redirect:/cuochi/" + cuocoId;
//    }



    @GetMapping("/admin/addRicetta/{cuoco_id}")
    public String formNuovaRicettaAdmin(@PathVariable("cuoco_id") Long id,Model model) {
        User user = getCredentials().getUser();
        if (getCredentials().isAdmin()
        && cuocoService.findByNomeAndCognome(user.getNome(), user.getCognome()).getId() != id) {
            model.addAttribute("cuoco", this.cuocoService.findById(id));
            model.addAttribute("ricetta", new Ricetta());
            return "forms/formNuovaRicettaAdmin.html";
        }
        else {
            model.addAttribute("messaggioErrore", "Non disponi per le autorizzazioni necessarie per questa operazione!");
            return "messaggioErrore";
        }
    }
    @PostMapping("/admin/addRicetta/{cuoco_id}")
    public String addRicettaAdmin(@PathVariable("cuoco_id") Long id, @ModelAttribute Ricetta ricetta) {
        ricetta.setCuoco(this.cuocoService.findById(id));
        this.ricettaService.save(ricetta);
        return "redirect:/cuochi/" + id;
    }

}

package it.uniroma3.siwfood.controller;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Immagine;
import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.ImmagineService;
import it.uniroma3.siwfood.service.RicettaService;
import it.uniroma3.siwfood.service.auth.UserService;


@Controller
public class CuocoController {

    @Autowired
    private CuocoService cuocoService;

    @Autowired
    private RicettaService ricettaService;

    @Autowired
    private ImmagineService immagineService;

    @Autowired
    private UserService userService;


    @GetMapping("/cuochi")
    public String getCuochi(Model model) {
        model.addAttribute("cuochi", this.cuocoService.findAll());
        return "cuochi.html";
    }

    @GetMapping("/cuochi/{id}")
    public String getCuoco(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cuoco", this.cuocoService.findById(id));
        return "cuoco.html";
    }

    @GetMapping("/cuochi/search")
    public String getFormSearchCuoco() {
        return "forms/formCercaCuochi.html";
    }

    @PostMapping("/cuochi/byNomeAndCognome")  
    public String postCuochiByNome(@RequestParam String nome, @RequestParam String cognome, Model model) {
        model.addAttribute("cuochi", this.cuocoService.findAllByNomeAndCognome(nome, cognome));
        return "cuochi.html";
    }
    
    //@PostMapping("/cuochi/byYear")
    //public String postCuochiByYear(@RequestParam int year, Model model) {
    //    model.addAttribute("cuochi", this.cuocoService.findByDataNascita(year));
    //    return "cuochi.html";
    //}

    @GetMapping("/admin/addCuoco")
    public String getFormNewCuoco(Model model) {
        model.addAttribute("cuoco", new Cuoco());
        return "forms/formNuovoCuoco.html";
    }

    @PostMapping("/admin/addCuoco")
    public String postNewCuoco(@ModelAttribute Cuoco cuoco, @RequestParam("immagine") MultipartFile immagine) throws IOException {
        
        if (!immagine.isEmpty()) {
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (cuoco.getImmagini() == null) {
                cuoco.setImmagini(new ArrayList<>());
            }
            cuoco.getImmagini().add(img);
            immagineService.save(img);
        }
        cuocoService.save(cuoco);
        return "redirect:/cuochi/" + cuoco.getId();
    }

    @GetMapping("/admin/editCuoco/{id}")
    public String getFormEditCuoco(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cuoco", this.cuocoService.findById(id));
        return "forms/formEditCuoco.html";
    }


    @PostMapping("/admin/editCuoco/{id}")
    public String updateCuoco(@PathVariable("id") Long id, @ModelAttribute Cuoco cuoco) {
        cuoco.setId(id);
        this.cuocoService.save(cuoco);  
        return "redirect:/cuochi/" + cuoco.getId();
    }

    @GetMapping("/admin/deleteCuoco/{id}")
    public String deleteCuocoById(@PathVariable("id") Long id) {
        cuocoService.deleteById(id);
        return "redirect:/cuochi";
    }


    @GetMapping("/cuoco/addRicetta/{cuoco_id}/{utente_id}")
    public String getCuocoFormNewRicetta(@PathVariable("cuoco_id") Long idC, @PathVariable("utente_id") Long idU,Model model) {
        
        if(!(this.cuocoService.findById(idC).equals(this.userService.findById(idU).getCuoco()))){
            return "redirect:/cuochi/" + idC;
        }
        
        model.addAttribute("cuoco", this.cuocoService.findById(idC));
        model.addAttribute("ricetta", new Ricetta());
        return "forms/formNewRicettaCuoco.html";
    }


    @PostMapping("/cuoco/addRicetta/{cuoco_id}")
    public String postCuocoNewRicetta(@PathVariable("cuoco_id") Long id, @ModelAttribute Ricetta ricetta) {
        ricetta.setCuoco(this.cuocoService.findById(id));
        this.ricettaService.save(ricetta);
        return "redirect:/cuochi/" + id;
    }

        
}

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
    public String cuochi(Model model) {
        model.addAttribute("cuochi", this.cuocoService.findAll());
        return "cuochi.html";
    }

   
    
    @GetMapping("/cuochi/{cuoco_id}")
    public String cuoco(@PathVariable("cuoco_id") Long id, Model model) {
        model.addAttribute("cuoco", this.cuocoService.findById(id));
        return "cuoco.html";
    }



    @GetMapping("/cuochi/search")
    public String search() {
        return "forms/formCercaCuochi.html";
    }
    @PostMapping("/cuochi/byNomeAndCognome")  
    public String byNome(@RequestParam String nome, @RequestParam String cognome, Model model) {
        model.addAttribute("cuochi", this.cuocoService.findAllByNomeAndCognome(nome, cognome));
        return "cuochi.html";
    }



    @GetMapping("/admin/addCuoco")
    public String formNuovoCuocoAdmin(Model model) {
        model.addAttribute("cuoco", new Cuoco());
        return "forms/formNuovoCuoco.html";
    }
    @PostMapping("/admin/addCuoco")
    public String formNuovoCuocoAdmin(@ModelAttribute Cuoco cuoco, @RequestParam("immagine") MultipartFile immagine) throws IOException {
        
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



    @GetMapping("/admin/editCuoco/{cuoco_id}")
    public String formModificaCuocoAdmin(@PathVariable("cuoco_id") Long id, Model model) {
        model.addAttribute("cuoco", this.cuocoService.findById(id));
        return "forms/formModificaCuocoAdmin.html";
    }
    @PostMapping("/admin/editCuoco/{cuoco_id}")
    public String editCuocoAdmin(@PathVariable("cuoco_id") Long id, @ModelAttribute Cuoco cuoco) {
        cuoco.setId(id);
        this.cuocoService.save(cuoco);  
        return "redirect:/cuochi/" + cuoco.getId();
    }



    @GetMapping("/admin/deleteCuoco/{cuoco_id}")
    public String deleteCuocoAdmin(@PathVariable("cuoco_id") Long id) {
        cuocoService.deleteById(id);
        return "redirect:/cuochi";
    }
        
}

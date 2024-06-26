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

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.repository.CuocoRepository;
import it.uniroma3.siwfood.service.CuocoService;



@Controller
public class CuocoController {
    
    @Autowired
    private CuocoService cuocoService;

    @Autowired
    private CuocoRepository cuocoRepository;

    @GetMapping("")
    public String getHome() {
        return "index.html";
    }
    
    @GetMapping("/cuochi")
    public String getCuochi(Model model) {
        model.addAttribute("cuochi", this.cuocoService.findAll());
        return "cuochi.html";
    }
    
    @GetMapping("/cuoco/{id}")
    public String getCuoco(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cuoco", this.cuocoService.findById(id));
        return "cuoco.html";
    }



    @GetMapping("/formNuovoCuoco")
    public String formNuovoCuoco(Model model) {
        model.addAttribute("cuoco", new Cuoco());
        return "formNuovoCuoco.html";
    }
    
    
    @PostMapping("/cuoco/aggiunto")
    public String formNuovoCuoco(@ModelAttribute("cuoco") Cuoco cuoco, Model model) {
        if(!cuocoRepository.existsByNomeAndCognome(cuoco.getNome(), cuoco.getCognome())) {
            this.cuocoService.save(cuoco);
            model.addAttribute("cuoco", cuoco);
            return "redirect:/cuochi"; //da migliorare con "redirect:/cuoco"+getId();
        }
        else {
            model.addAttribute("messaggioErrore", "Questo cuoco già è presente");
            return "formNuovoCuoco.html";
        }
    }

    @GetMapping("/cuochi/cercaByNomeAndCognome")
    public String getFormCercaCuochi() {
        return "formCercaCuochi.html";
    }

    @PostMapping("/cuochi/trovati")
    public String getCuochiByNomeAndCognome(@RequestParam String nome, @RequestParam String cognome, Model model) {
        model.addAttribute("cuochi", this.cuocoService.findAllByNomeAndCognome(nome, cognome));
        return "cuochi.html";
    }
    

}

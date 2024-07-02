package it.uniroma3.siwfood.controller;

import java.io.IOException;

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
import it.uniroma3.siwfood.repository.CuocoRepository;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.ImmagineService;
import it.uniroma3.siwfood.service.RicettaService;
import it.uniroma3.siwfood.service.UserService;



@Controller
public class CuocoController {
    
    @Autowired
    private CuocoService cuocoService;

    @Autowired
    private CuocoRepository cuocoRepository;

    @Autowired
    private RicettaService ricettaService;

    @Autowired
    private ImmagineService immagineService;

    @Autowired
    private UserService userService;
    
    @GetMapping("")
    public String index(Model model) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
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



    @GetMapping("forms/formNuovoCuoco")
    public String formNuovoCuoco(Model model) {
        model.addAttribute("cuoco", new Cuoco());
        return "forms/formNuovoCuoco.html";
    }
    
    
    @PostMapping("/cuoco/aggiunto")
    public String formNuovoCuoco(@ModelAttribute("cuoco") Cuoco cuoco, @RequestParam("immagine") MultipartFile immagine, Model model) throws IOException {
        if(!cuocoRepository.existsByNomeAndCognome(cuoco.getNome(), cuoco.getCognome())) {
            if(!immagine.isEmpty()) {
                Immagine i = new Immagine();
                i.setNome(immagine.getOriginalFilename());
                i.setDati(immagine.getBytes());
                cuoco.addImmagine(i);
                this.immagineService.save(i);
            }
            this.cuocoService.save(cuoco);
            model.addAttribute("cuoco", cuoco);
            return "redirect:/cuochi"; //da migliorare con "redirect:/cuoco"+getId();
        }
        else {
            model.addAttribute("messaggioErrore", "Questo cuoco già è presente");
            return "forms/formNuovoCuoco.html";
        }
    }

    @GetMapping("/cuoco/{id}/modifica")
    public String formModificaCuoco(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cuoco", this.cuocoService.findById(id));
        return "forms/formModificaCuoco.html";
    }
    
    @PostMapping("/cuoco/{id}/aggiorna")
    public String aggiornaCuoco(@PathVariable("id") Long id, @ModelAttribute Cuoco cuoco) {
        cuoco.setId(id);
        this.cuocoService.save(cuoco);
        return "redirect:/cuoco/" + cuoco.getId();
    }

    @GetMapping("/cuochi/cercaByNomeAndCognome")
    public String getFormCercaCuochi() {
        return "forms/formCercaCuochi.html";
    }

    @PostMapping("/cuochi/trovati")
    public String getCuochiByNomeAndCognome(@RequestParam String nome, @RequestParam String cognome, Model model) {
        model.addAttribute("cuochi", this.cuocoService.findAllByNomeAndCognome(nome, cognome));
        return "cuochi.html";
    }
    
    @GetMapping("/cuoco/{id}/elimina")
    public String eliminaCuocoById(@PathVariable("id") Long id) {
        this.cuocoService.deleteById(id);
        return "redirect:/cuochi";
    }


    @GetMapping("/cuoco/{cuocoId}/aggiungiRicetta/{utenteId}")
    public String aggiungiRicettaACuoco(@PathVariable("id") Long cuocoId, @PathVariable("utenteId") Long utenteId, Model model) {
        model.addAttribute("ricetta", new Ricetta());
        model.addAttribute("cuoco", this.cuocoService.findById(cuocoId));
        model.addAttribute("utente", this.userService.findById(utenteId));
        if(this.cuocoService.findById(cuocoId).equals(this.userService.findById(utenteId).getCuoco())) {
            return "forms/formNuovaRicettaPerCuoco.html";
        }
        else {
            model.addAttribute("messaggioErrore", "Non disponi delle autorizzazioni per aggiungere una ricetta a questo cuoco!");
            return "messaggioErrore";
        }
    }

    @PostMapping("/cuoco/{id}/aggiungiRicetta")
    public String aggiungiRicettaACuoco(@PathVariable("id") Long id, @ModelAttribute Ricetta ricetta) {

        Cuoco cuoco = this.cuocoService.findById(id);
        ricetta.setCuoco(cuoco);
        this.ricettaService.save(ricetta);
        cuoco.addRicetta(ricetta);
        return "redirect:/ricetta/" + ricetta.getId();
    }



}

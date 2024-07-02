package it.uniroma3.siwfood.controller;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Immagine;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.ImmagineService;


@Controller
public class CuocoController {

    @Autowired
    private CuocoService cuocoService;

    @Autowired
    private ImmagineService immagineService;


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
        return "forms/formSearchCuoco.html";
    }

    @PostMapping("/cuochi/byNome")  
    public String postCuochiByNome(@RequestParam String nome, Model model) {
        model.addAttribute("cuochi", this.cuocoService.findByNome(nome));
        return "cuochi.html";
    }
    
    @PostMapping("/cuochi/byYear")
    public String postCuochiByYear(@RequestParam int year, Model model) {
        model.addAttribute("cuochi", this.cuocoService.findByDataNascita(year));
        return "cuochi.html";
    }

    @GetMapping("/admin/addCuoco")
    public String getFormNewCuoco(Model model) {
        model.addAttribute("cuoco", new Cuoco());
        return "forms/formNewCuoco.html";
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
        cuocoService.saveCuoco(cuoco);
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
        this.cuocoService.saveCuoco(cuoco);  
        return "redirect:/cuochi/" + cuoco.getId();
    }

    @GetMapping("/admin/deleteCuoco/{id}")
    public String deleteCuocoById(@PathVariable("id") Long id) {
        cuocoService.deleteCuoco(id);
        return "redirect:/cuochi";
    }

        
}

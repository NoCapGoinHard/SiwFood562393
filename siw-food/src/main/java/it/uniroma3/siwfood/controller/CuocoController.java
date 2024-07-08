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
import it.uniroma3.siwfood.model.auth.User;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.ImmagineService;


@Controller
public class CuocoController extends GlobalController{

    @Autowired
    private CuocoService cuocoService;

//   @Autowired
//   private RicettaService ricettaService;

    @Autowired
    private ImmagineService immagineService;



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
        model.addAttribute("cuochi", this.cuocoService.findByNomeAndCognome(nome, cognome));
        return "cuochi.html";
    }



    @GetMapping("/admin/addCuoco")
    public String formNuovoCuocoAdmin(Model model) {
        if(getCredentials().isAdmin()) {
            model.addAttribute("cuoco", new Cuoco());
            return "forms/formNuovoCuocoAdmin.html";
        }
        else {
            model.addAttribute("messaggioErrore", "Non disponi per le autorizzazioni necessarie per questa operazione!");
            return "cuochi.html";
        }
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



//    @GetMapping("/admin/editCuoco/{cuoco_id}")
//    public String formModificaCuocoAdmin(@PathVariable("cuoco_id") Long id, Model model) {
//        model.addAttribute("cuoco", this.cuocoService.findById(id));
//        return "forms/formModificaCuocoAdmin.html";
//    }
//    @PostMapping("/admin/editCuoco/{cuoco_id}")
//    public String editCuocoAdmin(@PathVariable("cuoco_id") Long id, @ModelAttribute Cuoco cuoco) {
//        cuoco.setId(id);
//        this.cuocoService.save(cuoco);  
//        return "redirect:/cuochi/" + cuoco.getId();
//    }

    @GetMapping("/admin/editCuoco/{cuoco_id}")
    public String getFormEditCuoco(@PathVariable("cuoco_id") Long id, Model model) {
        User user = getCredentials().getUser();
        if (getCredentials().isAdmin()
        || cuocoService.findByNomeAndCognome(user.getNome(), user.getCognome()).getId() == id) {
            model.addAttribute("cuoco", this.cuocoService.findById(id));
            return "formEditCuoco.html";
        }
        else {
            model.addAttribute("messaggioErrore", "Non disponi per le autorizzazioni necessarie per questa operazione!");
            return "redirect:/cuochi/" + id;
        }
    }
    @PostMapping("/admin/editCuoco/{cuoco_id}")
    public String updateCuoco(@PathVariable("cuoco_id") Long id, @ModelAttribute Cuoco cuoco,
            @RequestParam("immagine") MultipartFile immagine)
            throws IOException {
        cuoco.setId(id);

        if (!immagine.isEmpty()) {
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (cuoco.getImmagini().isEmpty()) {
                cuoco.getImmagini().add(img);
            } else {
                cuoco.getImmagini().clear();
                cuoco.getImmagini().add(img);
            }
            immagineService.save(img);
        }

        this.cuocoService.save(cuoco);
        return "redirect:/cuochi/" + cuoco.getId();
    }



//    @GetMapping("/cuoco/editCuoco/{cuoco_id}/{user_id}")
//    public String formModificaCuocoDalCuoco(@PathVariable("cuoco_id") Long cuoco_id, @PathVariable("user_id") Long user_id, Model model) {
//        Cuoco cuoco = this.cuocoService.findById(cuoco_id);
//        if(userService.findById(user_id).getCuoco().equals(cuoco)) {
//            model.addAttribute("cuoco", this.cuocoService.findById(cuoco_id));
//            return "forms/formModificaCuocoDalCuoco.html";
//        }
//        else return "redirect:/cuochi";
//    }
//    @PostMapping("/cuoco/editCuoco/{cuoco_id}")
//    public String editCuoco(@PathVariable("cuoco_id") Long id, @ModelAttribute Cuoco cuoco) {
//        cuoco.setId(id);
//        this.cuocoService.save(cuoco);  
//        return "redirect:/cuochi/" + cuoco.getId();
//    }


    //DAL SISTEMA
    @GetMapping("/admin/deleteCuoco/{cuoco_id}")
    public String deleteCuocoAdmin(@PathVariable("cuoco_id") Long id, Model model) {
        User user = getCredentials().getUser();
        if (getCredentials().isAdmin()
        || cuocoService.findByNomeAndCognome(user.getNome(), user.getCognome()).getId() == id) {
            this.cuocoService.deleteById(id);
            return "redirect:/cuochi";
        }
        else {
            model.addAttribute("messaggioErrore", "Non disponi per le autorizzazioni necessarie per questa operazione!");
        return "redirect:/cuochi/" + id;
        }
    }
        
}

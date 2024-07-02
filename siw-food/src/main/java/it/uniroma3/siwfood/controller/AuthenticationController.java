package it.uniroma3.siwfood.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.auth.Credentials;
import it.uniroma3.siwfood.model.auth.User;
import it.uniroma3.siwfood.service.CredentialsService;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.UserService;

@Controller
public class AuthenticationController {
    
    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private UserService userService;
    @Autowired
    private CuocoService cuocoService;
    
    /* 
    @GetMapping("")
    public String index(Model model) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
        return "index.html";
    }*/

    @GetMapping("/auth/registrati")
    public String registrati (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("cuoco", new Cuoco());
        model.addAttribute("credentials", new Credentials());
        return "/auth/registrati.html";
    }
    @PostMapping("/auth/registrati")
    public String registerUser(@ModelAttribute("user") User user, @ModelAttribute("credentials") Credentials credentials, @ModelAttribute("cuoco") Cuoco cuoco, Model model) {

            user.setCuoco(cuoco);
            userService.save(user);
            credentials.setUser(user);

            cuoco.setUser(user);
            cuoco.setNome(user.getNome());
            cuoco.setCognome(user.getCognome());
            cuoco.setDataNascita(user.getDataNascita());
            cuocoService.save(cuoco);

            credentials.setRuolo(Credentials.UTENTE_CUOCO);
            credentialsService.save(credentials);
            model.addAttribute("user", user);

            return "redirect:/index.html";
    }

    @GetMapping("/auth/accedi")
    public String accedi (Model model) {
        return "/auth/accedi.html";
    }

    @PostMapping("/auth/logged")
    public String defaultAfterLogin(Model model) {

        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());

        return "index.html";
    }

}
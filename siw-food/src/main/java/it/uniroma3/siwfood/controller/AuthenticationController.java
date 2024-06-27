package it.uniroma3.siwfood.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siwfood.model.auth.Credentials;
import it.uniroma3.siwfood.model.auth.User;
import it.uniroma3.siwfood.service.CredentialsService;
import it.uniroma3.siwfood.service.UserService;

@Controller
public class AuthenticationController {
    
    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String index(Model model) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
        return "index.html";
    }

    @GetMapping("/registrati")
    public String registrati (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "registrati.html";
    }

    @GetMapping("/accedi")
    public String accedi (Model model) {
        return "accedi.html";
    }


    @GetMapping("/success")
    public String defaultAfterLogin(Model model) {

        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Credentials> credentials = credentialsService.findByUsername(userDetails.getUsername());

        return "index.html";
    }

    @PostMapping("/registrati")
    public String registerUser(@ModelAttribute("user") User user,
                               BindingResult userBindingResult,
                               @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

         if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            userService.save(user);
            credentials.setUser(user);

            credentials.setRuolo(Credentials.UTENTE_VISITATORE);
            credentialsService.save(credentials);
            model.addAttribute("user", user);

            return "redirect:/";
        }
        return "registrati.html";
    }
}
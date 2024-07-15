package it.uniroma3.siwfood.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.auth.Credentials;
import it.uniroma3.siwfood.model.auth.User;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.auth.CredentialsService;
import it.uniroma3.siwfood.service.auth.UserService;


@Controller
public class AuthenticationController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CuocoService cuocoService;

    

    @GetMapping("/")
    public String getHomePage() {

     //   @SuppressWarnings("unused")
      //  Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 

        return "index.html";
    }

    //LOGIN
    @GetMapping("/login")
    public String getLoginForm(Model model) {
        return "auth/login.html";
    }

    @GetMapping("/success")
    public String getHomeAfterLogin(Model model) {
        
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        @SuppressWarnings("unused")
        Credentials credentials = this.credentialsService.findByUsername(userDetails.getUsername());

        return "index.html";
    }


    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());

        return "auth/register.html";
    }



    @PostMapping(value = { "/register" })
    public String registerUser(
            @ModelAttribute("user") User user,
            BindingResult userBindingResult,

            @ModelAttribute("credential") Credentials credentials,
            BindingResult credentialsBindingResult,

            Model model
            ) throws IOException {

        if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            userService.save(user);
            credentials.setUser(user);
            Cuoco cuoco = new Cuoco(user);
            cuocoService.save(cuoco);
            user.setCuoco(cuoco);
            credentials.setRuolo(Credentials.UTENTE_CUOCO);
            credentialsService.save(credentials);
            model.addAttribute("user", user);

            return "redirect:/";
        }
        return "register.html";
    }
    
    
}


package it.uniroma3.siwfood.controller;

import java.io.IOException;
import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Immagine;
import it.uniroma3.siwfood.model.auth.Credentials;
import it.uniroma3.siwfood.model.auth.User;
import it.uniroma3.siwfood.service.CredentialsService;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.ImmagineService;
import it.uniroma3.siwfood.service.UserService;
//import jakarta.validation.Valid;


@Controller
public class AuthenticationController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CuocoService cuocoService;

    @Autowired
    private ImmagineService immagineService;
    
    //RESTITUISCE IL TEMPLATE DELL'HOME PAGE
    @GetMapping("/")
    public String getHomePage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 

        return "home.html";
    }

    //LOGIN
    @GetMapping("/login")
    public String getLoginForm(Model model) {
        return "auth/login.html";
    }

    @GetMapping("/success")
    public String getHomeAfterLogin(Model model) {
        
        //capire come vengono passati  o comunque cosa ci fanno
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.findByUsername(userDetails.getUsername());

        return "home.html";
    }


    //REGISTRAZIONE
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        
        model.addAttribute("cuoco", new Cuoco());
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());

        return "auth/register.html";
    }
    
    @PostMapping("/register")
    public String postNewuser(@ModelAttribute("user") User user, 
                                BindingResult userBindingResult, 
                                @ModelAttribute("credentials") Credentials credentials, 
                                BindingResult credentialsBindingResult, 
                                @ModelAttribute("cuoco") Cuoco cuoco, 
                                @RequestParam("immagine") MultipartFile immagine,
                                Model model) throws IOException {

        if(!immagine.isEmpty()){
            Immagine img = new Immagine();
            img.setNome(immagine.getOriginalFilename());
            img.setDati(immagine.getBytes());
            if (cuoco.getImmagini() == null) {
                cuoco.setImmagini(new ArrayList<>());
            }
            cuoco.getImmagini().add(img);
            this.immagineService.save(img);
        }
        
        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()){
            
            
            //cuoco
            cuoco.setNome(user.getNome());
            cuoco.setCognome(user.getCognome());
            cuoco.setDataNascita(user.getDataNascita());
            cuocoService.save(cuoco);

            user.setCuoco(cuoco);
            userService.save(user);


            credentials.setUser(user);
            credentials.setRuolo(Credentials.UTENTE_CUOCO);
            
            credentialsService.save(credentials);
            
            model.addAttribute("user", user);

            return "redirect:/";
        }
        
        return "auth/register.html";
    }
    
    
    
}
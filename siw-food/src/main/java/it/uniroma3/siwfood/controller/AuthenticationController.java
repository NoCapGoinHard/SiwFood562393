package it.uniroma3.siwfood.controller;

import java.io.IOException;

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

        @SuppressWarnings("unused")
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 

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
    
//    @PostMapping("/register")
//    public String postNewUtente(@Valid @ModelAttribute("user") User user, 
//                                BindingResult utenteBindingResult, 
//                                @Valid @ModelAttribute("credentials") Credentials credentials, 
//                                BindingResult credentialsBindingResult, 
//                                @ModelAttribute("cuoco") Cuoco cuoco, 
//                                @RequestParam("immagine") MultipartFile immagine,
//                                Model model) throws IOException {
//
//        if(!immagine.isEmpty()){
//            Immagine img = new Immagine();
//            img.setFileName(immagine.getOriginalFilename());
//            img.setImageData(immagine.getBytes());
//            if (cuoco.getImmagini() == null) {
//                cuoco.setImmagini(new ArrayList<>());
//            }
//            cuoco.getImmagini().add(img);
//            this.immagineService.save(img);
//        }
//        
//        if(!utenteBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()){
//            
//            
//
//            cuoco.setNome(user.getNome());
//            cuoco.setCognome(user.getCognome());
//            cuoco.setDataNascita(user.getDataNascita());
//            cuocoService.save(cuoco);
//
//            user.setCuoco(cuoco);
//            userService.save(user);
//
//
//            credentials.setUser(user);
//            credentials.setRuolo(Credentials.UTENTE_CUOCO);
//            
//            credentialsService.save(credentials);
//            
//            model.addAttribute("user", user);
//
//            return "redirect:/";
//        }
//        
//        return "auth/register.html";
//    }


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
            credentials.setRuolo(Credentials.UTENTE_CUOCO);
            credentialsService.save(credentials);
            model.addAttribute("user", user);

            return "redirect:/";
        }
        return "register.html";
    }
    
    
}


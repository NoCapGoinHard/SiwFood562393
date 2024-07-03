package it.uniroma3.siwfood.service.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.auth.Credentials;
import it.uniroma3.siwfood.repository.auth.CredentialsRepository;

@Service
public class CredentialsService {
    
    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Credentials getCredentials(Long id){
        return this.credentialsRepository.findById(id).get();
    }

    public Credentials getCredentialsByUsername(String username){
        return this.credentialsRepository.findByUsername(username).get();
    }

    public Credentials saveCredentials(Credentials credentials){


        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    
    }


    public void deleteCredentials(Credentials credentials){
        this.credentialsRepository.delete(credentials);
    }


}
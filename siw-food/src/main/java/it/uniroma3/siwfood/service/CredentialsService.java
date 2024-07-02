package it.uniroma3.siwfood.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.auth.Credentials;
import it.uniroma3.siwfood.repository.CredentialsRepository;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Credentials findById(Long id) {
        return this.credentialsRepository.findById(id).get();
    }

    public Credentials findByUsername(String username) {
        return this.credentialsRepository.findByUsername(username).get();
    }

    public void save(Credentials credentials) {
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        this.credentialsRepository.save(credentials);
    }

    public void delete(Credentials credentials) {
        this.credentialsRepository.delete(credentials);
    }
}

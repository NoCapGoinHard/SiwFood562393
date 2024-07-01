package it.uniroma3.siwfood.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.auth.Credentials;
import it.uniroma3.siwfood.repository.CredentialsRepository;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;
    
    public Credentials findById(Long id) {
        return this.credentialsRepository.findById(id).get();
    }

    public Optional<Credentials> findByUsername(String username) {
        return this.credentialsRepository.findByUsername(username);
    }

    public void save(Credentials credentials) {
        this.credentialsRepository.save(credentials);
    }
}

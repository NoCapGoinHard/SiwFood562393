package it.uniroma3.siwfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.auth.User;
import it.uniroma3.siwfood.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User findById(Long id) {
        return this.userRepository.findById(id).get();
    }

    public User findByNome(String nome) {
        return this.userRepository.findByNome(nome);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }
}

package it.uniroma3.siwfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Allergene;
import it.uniroma3.siwfood.repository.AllergeneRepository;

@Service
public class AllergeneService {
    
    @Autowired
    private AllergeneRepository allergeneRepository;

    public Allergene findById(Long id) {
        return this.allergeneRepository.findById(id).get();
    }

    public Iterable<Allergene> findAll() {
        return this.allergeneRepository.findAll();
    }

    public void save(Allergene allergene) {
        this.allergeneRepository.save(allergene);
    }

    public Allergene findByNome(String nome) {
        return this.allergeneRepository.findByNome(nome);
    }
    
    public void deleteById(Long id) {
        this.allergeneRepository.deleteById(id);
    }

    public void deleteByNome(String nome) {
        this.allergeneRepository.deleteByNome(nome);
    }
}


package it.uniroma3.siwfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.repository.CuocoRepository;

@Service
public class CuocoService {
    
    @Autowired
    private CuocoRepository cuocoRepository;

    public Cuoco findById(Long id) {
        return this.cuocoRepository.findById(id).get();
    }

    public Iterable<Cuoco> findAll() {
        return this.cuocoRepository.findAll();
    }

    public void save(Cuoco cuoco) {
        this.cuocoRepository.save(cuoco);
    }

    public List<Cuoco> findAllByNomeAndCognome(String nome, String cognome) {
        return this.cuocoRepository.findAllByNomeAndCognome(nome, cognome);
    }

    public void deleteById(Long id) {
        this.cuocoRepository.deleteById(id);
    }

    

}
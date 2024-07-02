package it.uniroma3.siwfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.repository.RicettaRepository;

@Service
public class RicettaService {
    
    @Autowired
    private RicettaRepository ricettaRepository;

    public Ricetta findById(Long id) {
        return ricettaRepository.findById(id).get();
    }

    public Iterable<Ricetta> findAll() {
        return ricettaRepository.findAll();
    }

    public void save(Ricetta ricetta) {
        ricettaRepository.save(ricetta);
    }

    public List<Ricetta> findAllByNome(String nome) {
        return ricettaRepository.findAllByNome(nome);
    }

    public void deleteById(Long id) {
        ricettaRepository.deleteById(id);
    }
}
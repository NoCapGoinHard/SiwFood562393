package it.uniroma3.siwfood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Ingrediente;
import it.uniroma3.siwfood.repository.IngredienteRepository;

@Service
public class IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public Ingrediente findById(Long id) {
        return this.ingredienteRepository.findById(id).get();
    }

    public Optional<Ingrediente> findByNome(String nome) {
        return this.ingredienteRepository.findByNome(nome);
    }

    public Iterable<Ingrediente> findAll() {
        return this.ingredienteRepository.findAll();
    }

    public List<Ingrediente> findAllByNome(String nome) {
        return this.ingredienteRepository.findAllByNome(nome);
    }
    
    public List<String> findDistinctNomi() {
        return ingredienteRepository.findDistinctNomi();
    }

    public void save(Ingrediente ingrediente) {
        this.ingredienteRepository.save(ingrediente);
    }

    public void deleteById(Long id) {
        this.ingredienteRepository.deleteById(id);
    }

    public void deleteAllByNome(String nome) {
        this.ingredienteRepository.deleteAllByNome(nome);
    }


    //public Ingrediente findByRicettaAndNome(Ricetta ricetta, String nome) {
    //    return this.ingredienteRepository.findByRicettaAndNome(ricetta, nome);
    //}
}

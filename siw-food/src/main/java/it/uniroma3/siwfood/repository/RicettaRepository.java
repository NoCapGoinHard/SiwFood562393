package it.uniroma3.siwfood.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Ricetta;


public interface RicettaRepository extends CrudRepository<Ricetta, Long> {
    
    public List<Ricetta> findAllByNome(String nome);

    public boolean existsByNome(String nome);
}

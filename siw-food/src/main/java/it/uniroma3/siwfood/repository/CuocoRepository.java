package it.uniroma3.siwfood.repository;

import java.util.*;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Cuoco;

public interface CuocoRepository extends CrudRepository<Cuoco, Long> {
    
    public List<Cuoco> findByNome(String nome);

    
    public boolean existsByNomeCognome(String nome, String cognome);
}

package it.uniroma3.siwfood.repository;

import java.util.*;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Cuoco;

public interface CuocoRepository extends CrudRepository<Cuoco, Long> {

    public List<Cuoco> findAllByNomeAndCognome(String nome, String cognome);
    
    public boolean existsByNomeAndCognome(String nome, String cognome);
}

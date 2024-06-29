package it.uniroma3.siwfood.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Ingrediente;


public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {
    
    public Ingrediente findByNome(String nome);

    public List<Ingrediente> findAllByNome(String nome);

    @Query("SELECT DISTINCT i.nome FROM Ingrediente i")
    public List<String> findDistinctNomi();
    
    public boolean existsByNome(String nome);
    
}

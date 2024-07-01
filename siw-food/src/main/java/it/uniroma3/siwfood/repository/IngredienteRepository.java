package it.uniroma3.siwfood.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Ingrediente;
import jakarta.transaction.Transactional;


public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {
    
    public Optional<Ingrediente> findByNome(String nome);

    public List<Ingrediente> findAllByNome(String nome);

    @Query("SELECT DISTINCT i.nome FROM Ingrediente i")
    public List<String> findDistinctNomi();
    
    public boolean existsByNome(String nome);

    //public Ingrediente findByRicettaAndNome(Ricetta ricetta, String nome);
    @Modifying
    @Transactional
    @Query("DELETE FROM Ingrediente i WHERE i.nome = :nome")
    public void deleteAllByNome(String nome);
    
}

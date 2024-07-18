package it.uniroma3.siwfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Ricetta;


@Repository
public interface RicettaRepository extends CrudRepository<Ricetta,Long>{

    public Iterable<Ricetta> findByNome(String nome);

    @Query("SELECT r FROM Ricetta r JOIN r.ingredienti i WHERE i.nome = :nomeIngrediente")
    public Iterable<Ricetta> findByIngredienteNome(@Param("nomeIngrediente") String nomeIngrediente);
    
    public boolean existsByNomeAndCuoco(String nome, Cuoco cuoco);

    public List<Ricetta> findAllByNome(String nome);

    @Query(value = "SELECT * FROM ricetta WHERE nome LIKE :nome%", nativeQuery = true)
    public List<Ricetta> findAllByNomeSimile(@Param("nome") String nome);

}

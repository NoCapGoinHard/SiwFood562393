package it.uniroma3.siwfood.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwfood.model.Cuoco;


@Repository
public interface CuocoRepository extends CrudRepository<Cuoco, Long>{

    public boolean existsByNomeAndCognome(String nome,String cognome);

    public Cuoco findByNomeAndCognome(String nome, String cognome);

}
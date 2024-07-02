package it.uniroma3.siwfood.repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwfood.model.Cuoco;


@Repository
public interface CuocoRepository extends CrudRepository<Cuoco, Long>{

    public boolean existsByNomeAndCognome(String nome,String cognome);

    public List<Cuoco> findAllByNomeAndCognome(String nome, String cognome);

}
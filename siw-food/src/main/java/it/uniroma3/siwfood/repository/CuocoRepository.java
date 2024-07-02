package it.uniroma3.siwfood.repository;



import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwfood.model.Cuoco;


@Repository
public interface CuocoRepository extends CrudRepository<Cuoco, Long>{


    public Iterable<Cuoco> findByNomeOrderByCognomeAsc(String nome);

    public Iterable<Cuoco> findByDataNascitaAfter(LocalDate dataNascita);

    public boolean existsByNomeAndCognome(String nome,String cognome);

}
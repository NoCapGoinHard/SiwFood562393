package it.uniroma3.siwfood.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwfood.model.Allergene;

@Repository
public interface AllergeneRepository extends CrudRepository<Allergene, Long> {
    
    public Allergene findByNome(String nome);

    public boolean existsByNome(String nome);

    public void deleteByNome(String nome);

}
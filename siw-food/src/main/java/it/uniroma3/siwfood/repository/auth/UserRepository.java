package it.uniroma3.siwfood.repository.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwfood.model.auth.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByNome(String nome);
    
}

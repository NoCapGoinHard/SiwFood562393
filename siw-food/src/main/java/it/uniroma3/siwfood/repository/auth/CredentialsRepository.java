package it.uniroma3.siwfood.repository.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwfood.model.auth.Credentials;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Long>{
    

    public Credentials findByUsername(String username);


}


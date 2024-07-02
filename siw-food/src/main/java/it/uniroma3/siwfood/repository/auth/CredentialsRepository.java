package it.uniroma3.siwfood.repository.auth;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwfood.model.auth.Credentials;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Long>{
    

    public Optional<Credentials> findByUsername(String username);

    public Optional<Credentials> findById(Long id);

    public Credentials getCredentialsByUsername(String username);

}


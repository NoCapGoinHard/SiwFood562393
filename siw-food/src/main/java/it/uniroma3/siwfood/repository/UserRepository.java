package it.uniroma3.siwfood.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.auth.User;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByNome(String nome);

}

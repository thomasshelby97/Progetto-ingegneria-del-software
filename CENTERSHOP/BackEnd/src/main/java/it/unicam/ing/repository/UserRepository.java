package it.unicam.ing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.unicam.ing.models.MyUser;

@Repository
public interface UserRepository extends CrudRepository<MyUser, Long> {

	MyUser findByUsername(String s);
    
}

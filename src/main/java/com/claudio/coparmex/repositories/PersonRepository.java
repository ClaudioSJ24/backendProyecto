package com.claudio.coparmex.repositories;

import com.claudio.coparmex.models.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * No crea un Bean de este repositorio, solo crea los beans nesesarios de las clases hijas "notPartner y Partner"
 * */
@NoRepositoryBean
public interface PersonRepository extends CrudRepository<Person, Integer> {

    /**
     * Se establecen las consultas a relizar de la tabla persona, para utilizar estos metodos es necesario
     * definirlos en la interfas PersonDAO para su implementación desde la clase PersonDAOImp, Estos metodos son accesibles
     * desde los servicios de Partner y NotPartner.
     * */
    @Query("select p from Person p where p.name = ?1 and p.lastname=?2")
    Optional<Person> findNameLastname(String name, String lastname);
    @Query("select a from Person a where a.lastname=?1")
    Iterable<Person> findLastname(String lastname);

}

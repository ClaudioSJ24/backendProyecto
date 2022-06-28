package com.claudio.coparmex.services.contracts;


import com.claudio.coparmex.models.entities.Person;

import java.util.Optional;

public interface PersonDAO extends GenericDAO<Person>{

    /**
     * metodos comunes a implementar en PersonDAOImpl.
     * */
    Optional<Person> findNameLastname(String name, String lastname);
    Iterable<Person> findLastname(String lastname);

}

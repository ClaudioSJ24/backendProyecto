package com.claudio.coparmex.services.contracts;


import com.claudio.coparmex.models.entities.Person;

import java.util.Optional;

public interface PersonDAO {
    Optional<Person> findById(Integer id);
    Person save(Person person);
    Iterable<Person> findAll();
    void deleteById(Integer id);
}

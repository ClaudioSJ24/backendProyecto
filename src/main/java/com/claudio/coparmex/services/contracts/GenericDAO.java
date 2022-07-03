package com.claudio.coparmex.services.contracts;

import com.claudio.coparmex.models.entities.Person;

import java.util.Optional;

public interface GenericDAO<E> {

    Optional<E> findById(Integer id);
    E save(E entidad);
    Iterable<E> findAll();
    Optional<Person> deleteById(Integer id);
}

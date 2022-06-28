package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.repositories.PersonRepository;
import com.claudio.coparmex.services.contracts.PersonDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class PersonDAOImp extends GenericDAOImp<Person, PersonRepository> implements PersonDAO {


/**
 * Inyectar PersonRepository a traves de constructor
 * */
    public PersonDAOImp(PersonRepository genericRepository) {
        super(genericRepository);
    }

    /**
     * Implementacion de metodos de interfaz PersonaDAO
     * */
    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findNameLastname(String name, String lastname) {
        return genericRepository.findNameLastname(name, lastname);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findLastname(String lastname) {
        return genericRepository.findLastname(lastname);
    }
}

package com.claudio.coparmex.services.contracts;

import com.claudio.coparmex.models.entities.Person;

import java.util.Optional;

public interface PartnerDAO {

    /***
     * En esta interface se establecen todos los metodos que se tienen que implementar
     * en la clase PartnerDAOImp para su uso adecuado.
     * */

    Optional<Person> findById(Integer id);
    Person save(Person person);
    Iterable<Person> findAll();
    void deleteById(Integer id);


}

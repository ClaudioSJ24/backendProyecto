package com.claudio.coparmex.repositories;

import com.claudio.coparmex.models.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
/**
 * No crea un Bean de este repositorio, solo crea los beans nesesarios de las clases hijas "notPartner y Partner"
 * */
@NoRepositoryBean
public interface PersonRepository extends CrudRepository<Person, Integer> {
}

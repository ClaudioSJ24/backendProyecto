package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.models.entities.NotPartner;
import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.repositories.NotPartnerRepository;
import com.claudio.coparmex.repositories.PersonRepository;
import com.claudio.coparmex.services.contracts.NotPartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NotPartnerDAOImp implements NotPartnerDAO {
    /**
     * Se crea una instancia del repositorio para poder utilizar los metodos esenciales
     * de un crud que nos proporciona la clase NotPartnerRepository que hereda de PersonRepostory
     * que a su vez hereda de la clase CrudRepository
     * **/
    @Autowired
    @Qualifier(value = "notPartnersRepository")/** Nombre del Bean a utilizar de interfaz NotPartnerDAO*/
    private PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        personRepository.findById(id);
    }
}

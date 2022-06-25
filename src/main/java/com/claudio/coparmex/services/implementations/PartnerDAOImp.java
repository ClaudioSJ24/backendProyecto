package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.repositories.PartnerRepository;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.Optional;

@Service

public class PartnerDAOImp implements PartnerDAO {
    /**
     * Se crea una instancia del repositorio para poder utilizar los metodos esenciales
     * de un crud que nos proporciona la clase PartnerRepository que hereda de PersonRepostory
     * que a su vez hereda de la clase CrudRepository
     * **/
    @Autowired
    @Qualifier(value = "partnersRepository") /** Nombre del Bean a utilizar de interfaz PartnerDAO*/
    private PartnerRepository partnerRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findById(Integer id) {
        return partnerRepository.findById(id);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return partnerRepository.save(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findAll() {
        return partnerRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        partnerRepository.deleteById(id);
    }
}

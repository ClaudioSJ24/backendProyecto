package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.models.entities.Contact;
import com.claudio.coparmex.repositories.ContactRepository;
import com.claudio.coparmex.repositories.PersonRepository;
import com.claudio.coparmex.services.contracts.ContactDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContactDAOImp extends PersonDAOImp implements ContactDAO {

    @Autowired
    public ContactDAOImp(@Qualifier(value = "contactsRepository" )PersonRepository genericRepository){
        super(genericRepository);
    }
    @Override
    @Transactional(readOnly = true)
    public Iterable<Contact> getAllContact() {
        return ((ContactRepository)genericRepository).getAllContact();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contact> findByIdP(Integer idContact) {
        return ((ContactRepository)genericRepository).findByIdP(idContact);
    }
}

package com.claudio.coparmex.services.contracts;

import com.claudio.coparmex.models.entities.Contact;

import java.util.Optional;

public interface ContactDAO extends PersonDAO{

    Iterable<Contact> getAllContact();
    Optional<Contact> findByIdP(Integer idContact);

}

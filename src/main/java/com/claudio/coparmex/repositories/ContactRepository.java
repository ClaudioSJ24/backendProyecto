package com.claudio.coparmex.repositories;

import com.claudio.coparmex.models.entities.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "contactsRepository")
public interface ContactRepository extends PersonRepository{
    @Query("select c from Contact c join Person p on c.idP = p.idP")
    Iterable<Contact> getAllContact();

    Optional<Contact> findByIdP(Integer idContact);
}

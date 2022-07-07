package com.claudio.coparmex.services.contracts;

import com.claudio.coparmex.models.entities.Event;

import java.util.Optional;

public interface EventDAO {

    Optional<Event> findById(Integer id);
    Event save(Event event);
    Iterable<Event> findAll();
    Object deleteById(Integer id);
}

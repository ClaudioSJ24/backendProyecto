package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.models.entities.Event;
import com.claudio.coparmex.repositories.EventRepository;
import com.claudio.coparmex.services.contracts.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EventDAOImp implements EventDAO {

    @Autowired
    private EventRepository eventRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Event> findById(Integer id) {
        return eventRepository.findById(id);
    }

    @Override
    @Transactional
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    @Transactional
    public Object deleteById(Integer id) {
        eventRepository.deleteById(id);
        return null;
    }
}

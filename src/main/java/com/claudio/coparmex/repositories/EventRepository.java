package com.claudio.coparmex.repositories;

import com.claudio.coparmex.models.entities.Event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository <Event, Integer> {
}

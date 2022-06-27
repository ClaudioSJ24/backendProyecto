package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.services.contracts.GenericDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenericDAOImp<E,R extends CrudRepository<E,Integer>> implements GenericDAO<E> {

    protected final R genericRepository;

    public GenericDAOImp(R genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Integer id) {
        return genericRepository.findById(id);
    }

    @Override
    @Transactional
    public E save(E entidad) {
        return genericRepository.save(entidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return genericRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        genericRepository.deleteById(id);
    }
}

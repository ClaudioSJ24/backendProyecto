package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.models.entities.Rol;
import com.claudio.coparmex.models.entities.enumerators.RolName;
import com.claudio.coparmex.repositories.RolRepository;
import com.claudio.coparmex.services.contracts.RolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service



public class RolDAOImp implements RolDAO {

    @Autowired

    private RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Rol> findByNameRol(RolName rolName) {

        return rolRepository.findByNameRol(rolName);
    }

    @Override
    @Transactional
    public Rol save(Rol rol) {

        return rolRepository.save(rol);
    }
}

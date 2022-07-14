package com.claudio.coparmex.services.contracts;

import com.claudio.coparmex.models.entities.Rol;
import com.claudio.coparmex.models.entities.enumerators.RolName;
import com.claudio.coparmex.repositories.RolRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolDAO{

    Optional<Rol> findByNameRol(RolName rolName);
    Rol save(Rol rol);

}

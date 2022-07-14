package com.claudio.coparmex.repositories;

import com.claudio.coparmex.models.entities.Rol;
import com.claudio.coparmex.models.entities.enumerators.RolName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RolRepository extends CrudRepository <Rol, Integer>{

    Optional<Rol> findByNameRol(RolName rolName);

    Rol save(Rol rol);

}

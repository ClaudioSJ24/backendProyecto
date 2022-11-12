package com.claudio.coparmex.services.contracts;

import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.models.entities.Person;

import java.util.Optional;

/***
 * En esta interface se establecen todos los metodos de un crud basico heredados de PesonaDAO que se tienen que implementar
 * en la clase PartnerDAOImp para su uso adecuado, destacar que en esta interfaz se agregaran los metodos
 * personalizados (para una consulta en especial de los socios).
 * */

public interface PartnerDAO extends PersonDAO {




    /**
     * metodos comunes a implementar en PartnerDAOImpl.
     * */



    Iterable<Partner> getAllPartner();

    Optional<Partner> findByIdP(Integer idPartner);






    Optional <Partner> findByEmail(String email);


   /* Boolean existsPartnerByUser(String user);
    boolean existsByEmail(String email);

    boolean existsByPassword(String password);*/



}

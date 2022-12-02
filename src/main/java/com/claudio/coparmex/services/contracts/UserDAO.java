package com.claudio.coparmex.services.contracts;

import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.models.entities.User;
import com.claudio.coparmex.models.entities.Person;

import java.util.Optional;

public interface UserDAO extends PersonDAO {

    /***
     * En esta interface se establecen todos los metodos de un crud basico heredados de PesonaDAO que se tienen que implementar
     * en la clase NotPartnerDAOImp para su uso adecuado, destacar que en esta interfaz se agregaran los metodos
     * personalizados (para una consulta en especial de los no socios).
         * */


    /**
     * Metodo proveniente de NotPartnerRepositorio para su implementacion en la clase NotPartnerDAOImp
     */
    Optional<Person> findByName(String name);

        Iterable<User> getAllUsers();

    Optional<User> findByIdP(Integer id);

    Optional<User> findByUser(String user);

    Optional <User> findByPassword(String password);
    Optional <User> findByEmail(String email);
}

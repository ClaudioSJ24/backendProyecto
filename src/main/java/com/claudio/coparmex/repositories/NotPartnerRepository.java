package com.claudio.coparmex.repositories;

import com.claudio.coparmex.models.entities.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Asignamos nombre del repositorio para utilizar el Bean  mediante @Qualifier, debido a que de momento
 * esta clase hereda de persona y persona no genera un Bean
 * */
@Repository(value = "notPartnersRepository")
public interface NotPartnerRepository extends PersonRepository{

    /**
     * Esta consulta no necesita de la anotacion @Query debido a que son metodos ya establecidos de spring Data (JPA) que ya estan previamente
     * establecidos en la pagina https://docs.spring.io/spring-data/jpa/docs/3.0.0-M4/reference/html/#jpa.query-methods.query-creation
     * para su implementacion, destacar que los metodos de esta pagina para su correcto funcionamiento tienen que tener en el metodo y en el argumento
     * el nombre del campo a buscar, en este caso es name.
     * destacar que este metodo solo sera utilizado por los no socios (NotPartner),
     * Es necesario agregar este metodo en la interfaz NotPatnerDAO
     */

    Optional<Person> findByName(String name);
}

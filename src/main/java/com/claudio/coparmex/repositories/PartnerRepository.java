package com.claudio.coparmex.repositories;


import com.claudio.coparmex.models.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Asignamos nombre del repositorio para utilizar el Bean  mediante @Qualifier, debido a que de momento
 * esta clase hereda de persona y persona no genera un Bean
 * */
@Repository(value = "partnersRepository")
public interface PartnerRepository extends PersonRepository{

    /**
     * Se establecen las consultas a relizar de la tabla Partner, para utilizar estos metodos es necesario
     * definirlos en la interfas PartnerDAO para su implementación desde la clase PartnerDAOImp, Estos metodos son accesibles
     * solo para los servicios de Partner.
     * Destacar que los campos utilizados para realizar la consulta el campo tienen que tener el mismo nombre establecido en la clase
     * Event, es decir, se hace ca consulta con nameEvent
     * */
    @Query("select p from Partner p where p.event.nameEvent=?1")
    Iterable<Person> findPartnerEvent(String name);
}

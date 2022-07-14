package com.claudio.coparmex.repositories;


import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.models.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    /***
     *
     * SELECT d.name, e.name, e.email, e.address FROM department d LEFT JOIN employee e ON d.id = e.dept_id;
     */
    @Query("select p from Partner p join Person a  on p.idP = a.idP")
    Iterable<Partner> getAllPartner();

    Optional<Partner> findByIdP(Integer idPartner);

    @Query("select p from Partner p join fetch p.event e where e.idEvent = ?1")
    Iterable<Partner> findByEventId(Integer idE);

    Optional<Partner> findByUser(String user);

    Optional <Partner> findByEmail(String email);

    Optional<Partner> findByPassword(String password);
   /* @Query("select p from Partner p where p.user = ?1")
    Optional <Partner> partnerByUser(String user);
*/
   /* @Query("select p from Partner p where p.password = ?1")
    boolean existsByPassword(String password);*/





}

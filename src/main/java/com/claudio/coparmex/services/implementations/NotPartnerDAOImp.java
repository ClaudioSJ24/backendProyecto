package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.models.entities.NotPartner;
import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.repositories.NotPartnerRepository;
import com.claudio.coparmex.repositories.PersonRepository;
import com.claudio.coparmex.services.contracts.NotPartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NotPartnerDAOImp extends PersonDAOImp implements NotPartnerDAO {

    /**
     * Se crea una instancia del repositorio para poder utilizar los metodos esenciales
     * de un crud que nos proporciona la clase NotPartnerRepository que hereda de PersonRepostory
     * que a su vez hereda de la clase CrudRepository
     * **/
    @Autowired
    /** @Qualifier(value = "notPartnersRepository") Nombre del Bean a utilizar de interfaz NotPartnerDAO*/
    public NotPartnerDAOImp(@Qualifier(value = "notPartnersRepository")PersonRepository genericRepository) {
        super(genericRepository);
    }

    /**
     * Metodo proveniente de la interfaz NotPartnerDAO para su implementacion mediante este servicio,
     * es necesario hacer el casteo a NotPartnerRepository debido a que por defecto es un repositorio
     * de PersonRepository
     * */
    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByName(String name) {
        return ((NotPartnerRepository)genericRepository).findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<NotPartner> getAllNotPartner() {
        return ((NotPartnerRepository)genericRepository).getAllNotPartner();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotPartner> findByIdP(Integer id) {
        return ((NotPartnerRepository)genericRepository).findByIdP(id);
    }
}

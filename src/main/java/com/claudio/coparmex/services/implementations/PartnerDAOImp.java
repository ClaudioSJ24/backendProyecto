package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.repositories.PartnerRepository;
import com.claudio.coparmex.repositories.PersonRepository;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.Optional;

@Service()


public class PartnerDAOImp extends PersonDAOImp implements PartnerDAO {

    /**
     * Se crea una instancia del repositorio para poder utilizar los metodos esenciales
     * de un crud que nos proporciona la clase PartnerRepository que hereda de PersonRepostory
     * que a su vez hereda de la clase CrudRepository
     * **/
    @Autowired
    /** @Qualifier(value = "partnersRepository") Nombre del Bean a utilizar de interfaz PartnerDAO*/
    public PartnerDAOImp( @Qualifier(value = "partnersRepository") PersonRepository genericRepository) {
        super(genericRepository);
    }

    /**
     * Implementacion de metodos de interfaz PartnerDAO, destacar que se tiene que castear a PartnerRepository
     * debido a que por defecto utiliza PersonRepository
     * */
    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findPartnerEvent(String name) {
        return ((PartnerRepository)genericRepository).findPartnerEvent(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Partner> getAllPartner() {
        return ((PartnerRepository)genericRepository).getAllPartner();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Partner> findByIdP(Integer idP) {
        return ((PartnerRepository)genericRepository).findByIdP(idP);
    }


}

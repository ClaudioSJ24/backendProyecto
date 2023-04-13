package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.models.entities.User;
import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.repositories.UserRepository;
import com.claudio.coparmex.repositories.PersonRepository;
import com.claudio.coparmex.services.contracts.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service()
public class UserDAOImp extends PersonDAOImp implements UserDAO {

    /**
     * Se crea una instancia del repositorio para poder utilizar los metodos esenciales
     * de un crud que nos proporciona la clase NotPartnerRepository que hereda de PersonRepostory
     * que a su vez hereda de la clase CrudRepository
     * **/
    @Autowired
    /** @Qualifier(value = "usersRepository") Nombre del Bean a utilizar de interfaz NotPartnerDAO*/
    public UserDAOImp(@Qualifier(value = "usersRepository")PersonRepository genericRepository) {
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
        return ((UserRepository)genericRepository).findByName(name);
    }
    @Override
    @Transactional(readOnly = true)
    public Iterable<User> getAllUsers (){
        return ((UserRepository)genericRepository).getAllUsers();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByIdP(Integer id) {
        return ((UserRepository)genericRepository).findByIdP(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUser(String user) {
        return ((UserRepository)genericRepository).findByUser(user);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByPassword(String password) {
        return ((UserRepository)genericRepository).findByPassword(password);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return ((UserRepository)genericRepository).findByEmail(email);
    }


}

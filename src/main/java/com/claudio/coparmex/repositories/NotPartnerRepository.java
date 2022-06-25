package com.claudio.coparmex.repositories;

import org.springframework.stereotype.Repository;

/**
 * Asignamos nombre del repositorio para utilizar el Bean  mediante @Qualifier, debido a que de momento
 * esta clase hereda de persona y persona no genera un Bean
 * */
@Repository(value = "notPartnersRepository")
public interface NotPartnerRepository extends PersonRepository{


}

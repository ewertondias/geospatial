package com.sccon.geospatial.person.domain.port;

import com.sccon.geospatial.person.domain.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    List<Person> findAll();
    Optional<Person> save(Person person);
    void delete(Person person);
    Optional<Person> update(Person person);
    Person findById(Long id);

}

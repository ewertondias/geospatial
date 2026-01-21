package com.sccon.geospatial.person.domain.port;

import com.sccon.geospatial.person.domain.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    List<Person> findAll();
    Person save(Person person);
    void delete(Person person);
    Person update(Person person);
    Optional<Person> findById(Long id);

}

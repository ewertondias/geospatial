package com.sccon.geospatial.person.adapter.out.persistence;

import com.sccon.geospatial.person.domain.model.Person;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PersonRepositoryAdapter implements PersonRepository {

    private final Map<Long, Person> map = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(3);

    @Override
    public List<Person> findAll() {
        return List.of();
    }

    @Override
    public Optional<Person> save(Person person) {
        map.put(person.id().value(), person);

        var newPerson = map.get(person.id().value());

        return Optional.of(newPerson);
    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public Optional<Person> update(Person person) {
        return Optional.empty();
    }

    @Override
    public Person findById(Long id) {
        return null;
    }

}

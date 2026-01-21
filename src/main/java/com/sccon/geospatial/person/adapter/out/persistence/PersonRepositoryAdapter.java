package com.sccon.geospatial.person.adapter.out.persistence;

import com.sccon.geospatial.person.adapter.out.persistence.assembler.PersonPersistenceAssembler;
import com.sccon.geospatial.person.domain.model.Person;
import com.sccon.geospatial.person.domain.model.PersonId;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PersonRepositoryAdapter implements PersonRepository {

    private final Map<Long, PersonEntity> map = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(4);

    @Override
    public List<Person> findAll() {
        return map.values().stream()
            .map(PersonPersistenceAssembler::toDomain)
            .toList();
    }

    @Override
    public Person save(Person person) {
        var id = person.id().value();

        if (id == null) {
            id = idGenerator.get();
        }

        var entity = PersonPersistenceAssembler.toEntity(id, person);

        map.putIfAbsent(id, entity);

        idGenerator.accumulateAndGet(id + 1, Math::max);

        return PersonPersistenceAssembler.toDomain(entity);
    }

    @Override
    public void delete(Person person) {
        map.remove(person.id().value());
    }

    @Override
    public Person update(Person person) {
        var personEntity = PersonPersistenceAssembler.toEntity(person.id().value(), person);
        var updatedEntity = map.computeIfPresent(person.id().value(), (key, existingEntity) -> personEntity);

        if (updatedEntity == null) {
            throw new IllegalArgumentException("Value id " + person.id().value() + " does not exist.");
        }

        return PersonPersistenceAssembler.toDomain(updatedEntity);
    }

    @Override
    public Optional<Person> findById(PersonId id) {
        var personEntity = map.getOrDefault(id.value(), null);

        if (personEntity == null) {
            return Optional.empty();
        }

        var person = PersonPersistenceAssembler.toDomain(personEntity);

        return Optional.of(person);
    }

}

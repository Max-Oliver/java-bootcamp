package com.globant.bootcamps.springjpah2demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    private final PersonJpaRepository repository;

    @Autowired
    public PersonService(final PersonJpaRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAllPersons() {
        return repository.findAll();
    }

    public Person findPersonById(final Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    public Person createPerson(final Person person) {
        person.setId(null);
        return repository.save(person);
    }

    public Person updatePerson(final Person person) {
        final Person original = findPersonById(person.getId());
        if (Objects.isNull(original)) {
            return null;
        }
        return repository.save(person);
    }
}

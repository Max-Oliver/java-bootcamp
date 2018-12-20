package com.globant.bootcamps.springjpah2demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(
        path = "/person",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonRestController {

    private final PersonService service;

    public PersonRestController(final PersonService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getPersons() {
        return service.findAllPersons();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Person getPersonById(@PathVariable("id") final Long id) {
        final Person person = service.findPersonById(id);
        if (Objects.isNull(person)) {
            throw new PersonNotFoundException("PERSON ID NOT FOUND: " + id);
        }
        return person;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person postPerson(@RequestBody final Person person) {
        return service.createPerson(person);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Person putPerson(@PathVariable(name = "id") final Long id,
                            @RequestBody final Person person) {
        person.setId(id);
        final Person updated = service.updatePerson(person);
        if (Objects.isNull(updated)) {
            throw new PersonNotFoundException("PERSON ID NOT FOUND: " + id);
        }
        return updated;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class PersonNotFoundException extends RuntimeException {
        public PersonNotFoundException(String message) {
            super(message);
        }
    }

}

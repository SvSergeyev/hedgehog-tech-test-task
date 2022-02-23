package tech.sergeyev.hedgehogtechtesttask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.sergeyev.hedgehogtechtesttask.persistence.model.Person;
import tech.sergeyev.hedgehogtechtesttask.service.PersonService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleController.class);

    private final PersonService personService;

    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody @Valid Person person) {
        // TODO: можно хранить имена в lowercase
        //  так же можно перенести valid на dto
        Person newPerson = personService.addPerson(person);
        URI uri = URI.create("people/" + newPerson.getId());
        LOGGER.info("Created resource with URI: {} for person: {}", uri, newPerson);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id) {
        if (!personService.existsPersonById(id)) {
            LOGGER.info("Person with id={} cannot be deleted because it does not exist in the database", id);
            return ResponseEntity.notFound().build();
        }
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }
}

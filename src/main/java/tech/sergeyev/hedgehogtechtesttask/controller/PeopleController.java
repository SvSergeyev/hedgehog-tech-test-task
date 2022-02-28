package tech.sergeyev.hedgehogtechtesttask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.sergeyev.hedgehogtechtesttask.payload.PersonDto;
import tech.sergeyev.hedgehogtechtesttask.persistence.model.Person;
import tech.sergeyev.hedgehogtechtesttask.service.PersonService;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/people")
/*@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)*/
public class PeopleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleController.class);
    private final PersonService personService;

    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody @Valid PersonDto personDto) {
        LOGGER.info("Received a POST request to add user with data: {}", personDto);
        String name = personDto.getName().toLowerCase();
        if (personService.existsPersonByName(name)) {
            LOGGER.info("User with name={} already exists", name);
            return new ResponseEntity<>(
                    "User with name " + name + " already exist",
                    HttpStatus.FORBIDDEN);
        }
        Person newPerson = personService.addPerson(personDto);
        URI uri = URI.create("people/" + newPerson.getId());
        LOGGER.info("Created resource with URI: {} for user: {}", uri, newPerson);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable UUID id) {
        LOGGER.info("Received a DELETE request to delete user with id: {}", id);
        if (!personService.existsPersonById(id)) {
            LOGGER.info("Person with id={} cannot be deleted because it does not exist in the database", id);
            return ResponseEntity.notFound().build();
        }
        LOGGER.info("User with id={} has been deleted", id);
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }
}

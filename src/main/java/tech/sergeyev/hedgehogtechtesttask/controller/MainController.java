package tech.sergeyev.hedgehogtechtesttask.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.sergeyev.hedgehogtechtesttask.persistence.model.Person;
import tech.sergeyev.hedgehogtechtesttask.service.PersonService;

import java.net.URI;
import java.util.List;

@RestController
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
public class MainController {
    static Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    PersonService personService;

    public MainController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/exists")
    public ResponseEntity<?> existsUserByName(@RequestParam(value = "name") String name) {
        name = name.toLowerCase();
        LOGGER.info("Exists by part: {}, {}", name, personService.existsPersonByName(name));
        return personService.existsPersonByName(name)
                ? ResponseEntity.ok("User with part " + name + " exists")
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Person>> searchPeopleByNameFragment(@RequestParam(value = "part") String part) {
        List<Person> people = personService.findAllByNameFragment(part.toLowerCase());
        return ResponseEntity.ok(people);
    }

    @GetMapping("/docs")
    public ResponseEntity<Void> viewDocs() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create("https://documenter.getpostman.com/view/16427287/UVkqtFbq"))
                .build();
    }
}











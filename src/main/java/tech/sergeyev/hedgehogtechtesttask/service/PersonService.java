package tech.sergeyev.hedgehogtechtesttask.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.sergeyev.hedgehogtechtesttask.persistence.dao.PersonRepository;
import tech.sergeyev.hedgehogtechtesttask.persistence.model.Person;

import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
public class PersonService {
    static Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person addPerson(Person person) {
        LOGGER.info("New person added: " + person);
        return repository.save(person);
    }

    public Boolean existsPersonByName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return repository.existsPersonByName(name);
    }

    public Boolean existsPersonById(long id) {
        return repository.existsPersonById(id);
    }

    public void deletePersonById(long id) {
        LOGGER.info("Person with id={} has been deleted", id);
        repository.deleteById(id);
    }

    public List<Person> findAllByNameFragment(String name) {
        List<Person> people = repository.findAllByName(name).orElse(Collections.emptyList());
        LOGGER.info("For name (or fragment) [{}] found [{}] matches", name, people.size());
        return people;
    }
}

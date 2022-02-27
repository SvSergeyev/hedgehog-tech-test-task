package tech.sergeyev.hedgehogtechtesttask.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.sergeyev.hedgehogtechtesttask.payload.PersonDto;
import tech.sergeyev.hedgehogtechtesttask.persistence.dao.PersonRepository;
import tech.sergeyev.hedgehogtechtesttask.persistence.model.Person;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
/*@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)*/
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository repository;
    private final ObjectMapper mapper;

    public PersonService(PersonRepository repository,
                         ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Person addPerson(PersonDto personDto) {
        personDto.setName(personDto.getName().toLowerCase());
        personDto.setSurname(personDto.getSurname().toLowerCase());
        try {
            Person person = mapper.updateValue(new Person(), personDto);
            LOGGER.info("New person added: " + person);
            return repository.save(person);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean existsPersonByName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return repository.existsPersonByName(name);
    }

    public Boolean existsPersonById(UUID id) {
        return repository.existsPersonById(id);
    }

    public void deletePersonById(UUID id) {
        LOGGER.info("Person with id={} has been deleted", id);
        repository.deleteById(id);
    }

    public List<Person> findAllByNameFragment(String name) {
        List<Person> people = repository.findAllByName(name).orElse(Collections.emptyList());
        LOGGER.info("For name (or fragment) [{}] found [{}] matches", name, people.size());
        return people;
    }
}

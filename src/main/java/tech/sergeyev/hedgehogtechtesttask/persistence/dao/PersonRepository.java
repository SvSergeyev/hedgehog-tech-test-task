package tech.sergeyev.hedgehogtechtesttask.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.sergeyev.hedgehogtechtesttask.persistence.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    Boolean existsPersonByName(String name);
    Boolean existsPersonById(UUID id);
    @Query("select p from Person p where p.name like %:name%")
    Optional<List<Person>> findAllByName(@Param("name") String name);
}

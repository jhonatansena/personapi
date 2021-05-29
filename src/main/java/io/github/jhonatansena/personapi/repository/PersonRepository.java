package io.github.jhonatansena.personapi.repository;
import io.github.jhonatansena.personapi.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{


}

package io.github.jhonatansena.personapi.controller;

import io.github.jhonatansena.personapi.repository.PersonRepository;
import io.github.jhonatansena.personapi.entity.Person;
import io.github.jhonatansena.personapi.dto.MessageResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    PersonController(PersonRepository personRepository){

    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody Person person){
    Person savedPerson = personRepository.save(person);

    return MessageResponseDTO
                .builder()
                .message("Created person with ID "+ savedPerson.getId())
                .build();
    }

}

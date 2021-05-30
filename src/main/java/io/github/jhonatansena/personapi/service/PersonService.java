package io.github.jhonatansena.personapi.service;


import io.github.jhonatansena.personapi.dto.request.PersonDTO;
import io.github.jhonatansena.personapi.dto.response.MessageResponseDTO;
import io.github.jhonatansena.personapi.entity.Person;
import io.github.jhonatansena.personapi.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(  PersonDTO personDTO){
        Person savedPerson = personRepository.save(personDTO);

        return MessageResponseDTO.builder().message("Created person with ID "+ savedPerson.getId()).build();
    }


}

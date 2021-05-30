package io.github.jhonatansena.personapi.controller;

import io.github.jhonatansena.personapi.service.PersonService;
import io.github.jhonatansena.personapi.entity.Person;
import io.github.jhonatansena.personapi.dto.response.MessageResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;




@RestController
@RequestMapping("/api/v1/people")
public class PersonController {


    PersonService personService;

    @Autowired
    PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }




}

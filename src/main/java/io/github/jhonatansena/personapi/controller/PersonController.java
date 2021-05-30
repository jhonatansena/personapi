package io.github.jhonatansena.personapi.controller;

import io.github.jhonatansena.personapi.service.PersonService;
import io.github.jhonatansena.personapi.dto.request.PersonDTO;
import io.github.jhonatansena.personapi.dto.response.MessageResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import javax.validation.Valid;


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
    public MessageResponseDTO createPerson(@RequestBody @Valid  PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<PersonDTO> listAllPeople(){

       return  personService.listAllPeople();

    }




}

package io.github.jhonatansena.personapi.service;


import io.github.jhonatansena.personapi.dto.request.PersonDTO;
import io.github.jhonatansena.personapi.dto.response.MessageResponseDTO;
import io.github.jhonatansena.personapi.entity.Person;
import io.github.jhonatansena.personapi.repository.PersonRepository;
import io.github.jhonatansena.personapi.mapper.PersonMapper;
import io.github.jhonatansena.personapi.exception.PersonNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(  PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO.builder().message("Created person with ID "+ savedPerson.getId()).build();
    }


    @GetMapping
    public List<PersonDTO> listAllPeople(){
        List<Person> allPeople = personRepository.findAll();

        return  allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());

    }


    public PersonDTO listById(Long id) throws PersonNotFoundException{
        Optional<Person> optionalPerson = personRepository.findById(id);

        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException(id);
        }

        return personMapper.toDTO(optionalPerson.get());

    }




}

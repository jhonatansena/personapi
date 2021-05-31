package io.github.jhonatansena.personapi.service;


import io.github.jhonatansena.personapi.dto.request.PersonDTO;
import io.github.jhonatansena.personapi.dto.response.MessageResponseDTO;
import io.github.jhonatansena.personapi.entity.Person;
import io.github.jhonatansena.personapi.repository.PersonRepository;
import io.github.jhonatansena.personapi.mapper.PersonMapper;
import io.github.jhonatansena.personapi.exception.PersonNotFoundException;

import java.util.List;
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
        return createMessageResponse(savedPerson.getId(), " Created person with ID ");
    }


    @GetMapping
    public List<PersonDTO> listAllPeople(){
        List<Person> allPeople = personRepository.findAll();

        return  allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());

    }


    public PersonDTO listById(Long id) throws PersonNotFoundException{
      Person person = verifyIfAlredyExists(id);

        return personMapper.toDTO(person);

    }

    public void delete(Long id)throws PersonNotFoundException{
        verifyIfAlredyExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException{

        verifyIfAlredyExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), " Created person with ID ");
    }

    private Person verifyIfAlredyExists(Long id)throws PersonNotFoundException{

        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message){
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();

    }




}

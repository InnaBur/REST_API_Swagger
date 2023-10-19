package com.spring.firstSpring.service;

import com.spring.firstSpring.PersonRepository;
import com.spring.firstSpring.dto.PersonDTO;
import com.spring.firstSpring.entity.Person;
import com.spring.firstSpring.exceptions.PersonNotFoundException;
import com.spring.firstSpring.mapper.PersonMapperImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapperImpl personMapper;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapperImpl personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }


    @Override
    public ResponseEntity<Object> findById(Long id) {
        Optional<Person> personOptional = personRepository.findById(id);

        if (personOptional.isPresent()) {
            return ResponseEntity.ok(personOptional.get());
        } else {
            throw new PersonNotFoundException("Person not found");
        }

    }

    @Override
    public ResponseEntity<PersonDTO> findByName(String name) {
        Optional<Person> personOptional = personRepository.findPersonByName(name);
        return personOptional
                .map(person -> ResponseEntity.ok(personMapper.toDto(person)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<PersonDTO>> all() {
        List<Person> persons = personRepository.findAll();
        List<PersonDTO> personsDTOS = persons.stream()
                .map(personMapper::toDto)
                .toList();
        return ResponseEntity.ok(personsDTOS);
    }

    @Override
    public ResponseEntity<Void> createPerson(PersonDTO newPersonRequest, UriComponentsBuilder ucb) {
        Person person = personMapper.toEntity(newPersonRequest);

        Person savedPerson = personRepository.save(person);
        URI locationOfNewPerson = ucb
                .path("persons/{id}")
                .buildAndExpand(savedPerson.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewPerson).build();
    }

    @Override
    public ResponseEntity<Void> putPerson(Long id, PersonDTO personDto) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person updatedPerson = personMapper.toEntity(personDto);
            updatedPerson.setId(id);
            updatedPerson.setIpn(personDto.getIpn());
            updatedPerson.setName(personDto.getName());
            updatedPerson.setLastName(personDto.getLastName());
            personRepository.save(updatedPerson);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

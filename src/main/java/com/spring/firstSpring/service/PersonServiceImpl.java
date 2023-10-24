package com.spring.firstSpring.service;

import com.spring.firstSpring.config.PersonRepository;
import com.spring.firstSpring.dto.PersonDTO;
import com.spring.firstSpring.entity.Person;
import com.spring.firstSpring.exceptions.PersonNotFoundException;
import com.spring.firstSpring.exceptions.ValidateException;
import com.spring.firstSpring.mapper.PersonMapperImpl;
import com.spring.firstSpring.validation.MyValidator;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        throwExceptionIfPersonNotFound(personOptional);
        return ResponseEntity.ok(personOptional.get());
    }


    @Override
    public ResponseEntity<List<PersonDTO>> findByName(String name) {
        List<Person> persons = personRepository.findAll();
        List<PersonDTO> personsDTOS = persons.stream()
                .map(personMapper::toDto)
                .filter(person -> person.getName().equals(name))
                .toList();
        if (!personsDTOS.isEmpty()) {
            return ResponseEntity.ok(personsDTOS);
        } else {
            throw new PersonNotFoundException("Person " + name + " not found");
        }
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
        throwValidateExceptionIfInvalidData(newPersonRequest);

        Person person = personMapper.toEntity(newPersonRequest);
        Person savedPerson = personRepository.save(person);
        URI locationOfNewPerson = ucb
                .path("persons/id/{id}")
                .buildAndExpand(savedPerson.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewPerson).build();
    }

    @Override
    public ResponseEntity<Void> putPerson(Long id, PersonDTO personDto) {
        Optional<Person> personOptional = personRepository.findById(id);
        throwExceptionIfPersonNotFound(personOptional);

        Person person = personOptional.get();
        throwValidateExceptionIfInvalidData(personMapper.toDto(person));

        savePerson(personDto, id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new PersonNotFoundException("Person not found");
    }


    private void savePerson(PersonDTO personDto, Long id) {
        Person updatedPerson = personMapper.toEntity(personDto);
        updatedPerson.setId(id);
        updatedPerson.setIpn(personDto.getIpn());
        updatedPerson.setName(personDto.getName());
        updatedPerson.setLastName(personDto.getLastName());
        personRepository.save(updatedPerson);
    }

    private void throwValidateExceptionIfInvalidData(PersonDTO newPersonRequest) {
        MyValidator validator = new MyValidator();
        Set<ConstraintViolation<String>> validateIpnList = validator.validate(newPersonRequest.getIpn());
        if (!validateIpnList.isEmpty()) {
            throw new ValidateException("Inputted data is invalid");
        }
    }

    private void throwExceptionIfPersonNotFound(Optional<Person> personOptional) {
        if (personOptional.isEmpty()) {
            throw new PersonNotFoundException("Person not found");
        }
    }
}

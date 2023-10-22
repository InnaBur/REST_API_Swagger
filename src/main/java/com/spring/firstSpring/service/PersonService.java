package com.spring.firstSpring.service;

import com.spring.firstSpring.dto.PersonDTO;
import com.spring.firstSpring.exceptions.ValidateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface PersonService {


    ResponseEntity<Object> findById(Long id);
    ResponseEntity<List<PersonDTO>> findByName(String name);
    ResponseEntity<List<PersonDTO>> all();
    ResponseEntity<Void> createPerson(PersonDTO newPersonRequest, UriComponentsBuilder ucb) throws ValidateException;
    ResponseEntity<Void> putPerson(Long id, PersonDTO personDto);
    ResponseEntity<Void> deletePerson(Long id);
}

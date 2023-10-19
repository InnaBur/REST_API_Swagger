package com.spring.firstSpring.service;

import com.spring.firstSpring.dto.PersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface PersonService {


    ResponseEntity<Object> findById(Long id);
    ResponseEntity<PersonDTO> findByName(String name);
    ResponseEntity<List<PersonDTO>> all();
    ResponseEntity<Void> createPerson(PersonDTO newPersonRequest, UriComponentsBuilder ucb);
    ResponseEntity<Void> putPerson(Long id, PersonDTO personDto);
    ResponseEntity<Void> deletePerson(Long id);
}

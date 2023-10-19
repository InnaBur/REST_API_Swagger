package com.spring.firstSpring.controler;

import com.spring.firstSpring.dto.PersonDTO;
import com.spring.firstSpring.entity.Person;
import org.springframework.http.ResponseEntity;


public interface PersonService {

    ResponseEntity<PersonDTO> findById(Long id);
}

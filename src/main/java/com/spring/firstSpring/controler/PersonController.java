package com.spring.firstSpring.controler;

import com.spring.firstSpring.dto.PersonDTO;
import com.spring.firstSpring.validation.PersonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonServiceImpl personService;

    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PersonDTO> findByName(@PathVariable String name) {
        return personService.findByName(name);
    }

    @GetMapping
    protected ResponseEntity<List<PersonDTO>> all() {
        return personService.all();
    }

    @PostMapping
    private ResponseEntity<Void> createPerson(@Valid @RequestBody PersonDTO newPersonRequest, UriComponentsBuilder ucb) {
        return personService.createPerson(newPersonRequest, ucb);
    }


    @PutMapping("/{id}")
    private ResponseEntity<Void> putPerson(@PathVariable Long id, @Valid @RequestBody PersonDTO personDto) {
        return personService.putPerson(id, personDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }
}

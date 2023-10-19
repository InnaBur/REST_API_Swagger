package com.spring.firstSpring.controler;

import com.spring.firstSpring.PersonRepository;
import com.spring.firstSpring.dto.PersonDTO;
import com.spring.firstSpring.entity.Person;
import com.spring.firstSpring.mapper.PersonMapperImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import java.util.Optional;


@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonMapperImpl personMapper;

    public PersonController(PersonRepository personRepository, PersonMapperImpl personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {

        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            return ResponseEntity.ok(personOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> createPerson(@Valid @RequestBody PersonDTO newPersonRequest, UriComponentsBuilder ucb) {
        Person person = personMapper.toEntity(newPersonRequest);

//            Set<ConstraintViolation<String >> validateMessage = validator.validate(newPersonRequest.getIpn());
//
//            if (validateMessage.isEmpty()) {

        Person savedPerson = personRepository.save(person);
        URI locationOfNewPerson = ucb
                .path("persons/{id}")
                .buildAndExpand(savedPerson.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewPerson).build();
//            }
//            return ResponseEntity.notFound().build();
    }

    @GetMapping
    protected ResponseEntity<List<Person>> all() {
        return ResponseEntity.ok(personRepository.findAll());
    }

    //
//    @GetMapping
//    public ResponseEntity<List<CashCard>> findAll(Pageable pageable, Principal principal) {
//        Page<CashCard> page = cashCardRepository.findByOwner(principal.getName(),
//                PageRequest.of(
//                        pageable.getPageNumber(),
//                        pageable.getPageSize(),
//                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
//                ));
//        return ResponseEntity.ok(page.getContent());
//    }
//
    @PutMapping("/{id}")
    private ResponseEntity<Void> putPerson(@PathVariable Long id, @RequestBody Person person) {

        Optional<Person> personOptional = personRepository.findById(id);
        if (person != null) {
            Person updatedPerson = personOptional.get();
            updatedPerson.setId(person.getId());
            updatedPerson.setIpn(person.getIpn());
            updatedPerson.setName(person.getName());
            updatedPerson.setLastName(person.getLastName());
            personRepository.save(updatedPerson);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //
//    private CashCard findCashCard(Long requestedId, Principal principal) {
//        return cashCardRepository.findByIdAndOwner(requestedId, principal.getName());
//    }
//
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteCashCard(@PathVariable Long id) {

        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

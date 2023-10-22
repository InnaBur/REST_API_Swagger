//package com.spring.firstSpring.service;
//
//import com.spring.firstSpring.config.PersonRepository;
//import com.spring.firstSpring.entity.Person;
//import com.spring.firstSpring.mapper.PersonMapperImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class PersonServiceImplTest {
//
//    PersonRepository personRepository = mock(PersonRepository.class);
////    PersonMapperImpl personMapper = mock(PersonMapperImpl.class);
//
//    PersonServiceImpl personService = new PersonServiceImpl(personRepository, new PersonMapperImpl());
//
//    @Test
//    void findById() {
//        Person person = new Person("11", "Test", "Test");
//
//        when(personRepository.findById("1")).thenReturn(Optional.of(person));
//        Person testPerson = personRepository.save(person);
//
//        ResponseEntity<Object> response = personService.findById(testPerson.getId());
//
//        assertEquals(200, response.getStatusCode().value());
//    }
//
//    @Test
//    void findByName() {
//    }
//
//    @Test
//    void all() {
//    }
//
//    @Test
//    void createPerson() {
//    }
//
//    @Test
//    void putPerson() {
//    }
//
//    @Test
//    void deletePerson() {
//    }
//}
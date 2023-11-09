//package com.spring.firstSpring.service;
//
//import com.jayway.jsonpath.DocumentContext;
//import com.jayway.jsonpath.JsonPath;
//import com.spring.firstSpring.config.PersonRepository;
//import com.spring.firstSpring.dto.PersonDTO;
//import com.spring.firstSpring.entity.Person;
//import com.spring.firstSpring.exceptions.PersonNotFoundException;
//import com.spring.firstSpring.mapper.PersonMapperImpl;
//import jakarta.validation.ConstraintValidatorContext;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.util.UriComponents;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
////@SpringBootTest
//class PersonServiceImplTest {
//
//    @Mock
//    PersonRepository personRepository;
//
//    @Mock
//    PersonMapperImpl personMapper;
//
//    @InjectMocks
//    PersonServiceImpl personService;
//
//    @Mock
//    UriComponents uriComponent = mock(UriComponents.class);
//    @Mock
//    UriComponentsBuilder ucb = mock(UriComponentsBuilder.class);
//
//    @Mock
//    URI locationOfNewPerson;
//
//    @Test
//    void findByIdTest() {
//        Person person = new Person(10L, "1", "Name", "P");
//
//        when(personRepository.findById(10L)).thenReturn(Optional.of(person));
//        ResponseEntity<Object> response = personService.findById(person.getId());
//
//        assertEquals(200, response.getStatusCode().value());
//        verify(personRepository, times(1)).findById(10L);
//
//        Person gotPerson = (Person) response.getBody();
//        assertEquals("Name", gotPerson.getName());
//
//    }
//
//    @Test
//    void findByIdTestException() {
//
//        when(personRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(PersonNotFoundException.class, () -> personService.findById(1L));
//    }
//
//    @Test
//    void findByNameTest() {
//        Person person = new Person(10L, "1", "Ann", "P");
//        PersonDTO personDTO = new PersonDTO(10L, "1", "Ann", "P");
//        List<Person> list = new ArrayList<>(Arrays.asList(person));
//        List<PersonDTO> listDTO = new ArrayList<>(Arrays.asList(personDTO));
//
//        when(personRepository.findAll()).thenReturn(list);
//
//        ResponseEntity<List<PersonDTO>> response = personService.findByName("Ann");
//
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals("Ann", response.getBody().get(0).getName());
//
//        assertThrows(PersonNotFoundException.class, () -> personService.findByName("Bob"));
//    }
//
//    @Test
//    void allPersonsFoundTest() {
//
//        Person personOne = new Person(11L, "12", "One", "First");
//        Person personTwo = new Person(12L, "12", "One", "First");
//        List<Person> persons = new ArrayList<>(Arrays.asList(personOne, personTwo));
//
//        when(personRepository.findAll()).thenReturn(persons);
//
//        ResponseEntity<List<PersonDTO>> response = personService.all();
//        assertEquals(200, response.getStatusCode().value());
//
//        List<PersonDTO> personsDTOS = response.getBody();
//        assertEquals(2, personsDTOS.size());
//
//        verify(personRepository, times(1)).findAll();
//    }
//
//    @Test
//    void createPersonTestIfPersonCreated() throws URISyntaxException {
//        PersonDTO personDTO = new PersonDTO(11L, "10", "One", "First");
//        Person person = new Person(11L, "10", "One", "First");
//
//        when(ucb.path(anyString())).thenReturn(ucb);
//        when(ucb.buildAndExpand((Object) any())).thenReturn(uriComponent);
//        when(uriComponent.toUri()).thenReturn(locationOfNewPerson);
//        when(personRepository.save(any())).thenReturn(person);
//
//        ResponseEntity<Void> response = personService.createPerson(personDTO, ucb);
//        assertEquals(201, response.getStatusCode().value());
//    }
//
//
//    @Test
//    void createPersonTestIfPersonNotCreated() {
//    }
//
//    @Test
//    void putPersonTestIsPersonUpdated() {
//        PersonDTO personDTO = new PersonDTO(11L, "10", "One", "First");
//        Person person = new Person(11L, "10", "One", "First");
//        Person newPerson = new Person(11L, "100", "Two", "First");
//        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
//        when(personRepository.save(any())).thenReturn(newPerson);
//        when(personMapper.toDto(person))
//
//        ResponseEntity<Void> response = personService.putPerson(11L, personDTO);
//
//        assertEquals(204, response.getStatusCode().value());
//    }
//
//    @Test
//    void putPersonTestIsPersonNotUpdated() {
//        when(personRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(PersonNotFoundException.class, () -> personService.findById(1L));
//
//        PersonDTO personDTO = new PersonDTO(11L, "10", "One", "First");
//        Person person = new Person(11L, "10", "One", "First");
//        Person newPerson = new Person(11L, "100", "Two", "First");
//        when(personRepository.findById(11L)).thenReturn(Optional.of(person));
//        when(personRepository.save(any())).thenReturn(newPerson);
//
////        ResponseEntity<Void> response = personService.putPerson(12L, personDTO);
//        assertThrows(PersonNotFoundException.class, () -> personService.putPerson(12L, personDTO));
////        assertEquals(404, response.getStatusCode().value());
//    }
//
//    @Test
//    void deletePersonTest() {
//
//        Long id = 10L;
//        when(personRepository.existsById(id)).thenReturn(true);
//        ResponseEntity<Void> response = personService.deletePerson(id);
//        assertEquals(204, response.getStatusCode().value());
//    }
//}
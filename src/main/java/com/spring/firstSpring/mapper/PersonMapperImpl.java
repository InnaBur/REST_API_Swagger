package com.spring.firstSpring.mapper;

import com.spring.firstSpring.dto.PersonDTO;
import com.spring.firstSpring.entity.Person;

import org.springframework.stereotype.Component;

@Component
public class PersonMapperImpl implements PersonMapper{

    @Override
    public PersonDTO toDto(Person person) { //static
        PersonDTO personDTO = new PersonDTO();
        personDTO.setIpn(person.getIpn());
        personDTO.setName(person.getName());
        personDTO.setLastName(person.getLastName());
        return personDTO;
    }

    @Override
    public Person toEntity(PersonDTO personDTO) { //static
        Person person = new Person();
        person.setIpn(personDTO.getIpn());
        person.setName(personDTO.getName());
        person.setLastName(personDTO.getLastName());
        return person;
    }
}

package com.spring.firstSpring.mapper;

import com.spring.firstSpring.dto.PersonDTO;
import com.spring.firstSpring.entity.Person;

public interface PersonMapper {

    public PersonDTO toDto(Person person);
    public Person toEntity(PersonDTO personDTO);
}

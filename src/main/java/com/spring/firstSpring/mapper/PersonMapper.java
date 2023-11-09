package com.spring.firstSpring.mapper;

import com.spring.firstSpring.dto.PersonDTO;
import com.spring.firstSpring.entity.Person;

public interface PersonMapper {

    PersonDTO toDto(Person person);
    Person toEntity(PersonDTO personDTO);
}

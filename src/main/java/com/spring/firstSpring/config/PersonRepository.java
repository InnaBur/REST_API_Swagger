package com.spring.firstSpring.config;

import com.spring.firstSpring.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<List<Person>> findPersonByName(String name);
    boolean existsByIpn(String ipn);
}

package com.spring.firstSpring.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "Persons")
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ipn;

    @Column
    private String name;

    @Column (name = "last_name")
    private String lastName;

//    @Column
//    private int age;

    public Person() {
    }

    public Person(String ipn, String name, String lastName) {
        this.ipn = ipn;
        this.name = name;
        this.lastName = lastName;
    }


    public Long getId() {
        return id;
    }

    public String getIpn() {
        return ipn;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIpn(String ipn) {
        this.ipn = ipn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

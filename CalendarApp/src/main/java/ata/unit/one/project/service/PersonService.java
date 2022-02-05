package ata.unit.one.project.service;

import ata.unit.one.project.backend.Backend;
import ata.unit.one.project.backend.models.PersonDto;
import ata.unit.one.project.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {

    private final Backend backend;

    public PersonService(Backend backend) {
        this.backend = backend;
    }

    public List<Person> getPersons() {
        List<PersonDto> response = backend.getPersons();
        List<Person> listPersons = new ArrayList<>();
        for (PersonDto personDto : response) {
            Person person = Person.builder()
                    .setPersonId(personDto.getPersonId())
                    .setPersonName(personDto.getPersonName())
                    .build();
            listPersons.add(person);
        }
        return listPersons;
    }

    public Person addPerson(String personName) {
        PersonDto response = backend.addPerson(personName);
        Person person = Person.builder()
                .setPersonId(response.getPersonId())
                .setPersonName(response.getPersonName())
                .build();
        return person;
    }
}
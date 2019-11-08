package springTest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springTest.model.Person;
import springTest.repository.PersonRepository;


@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;


    public Person createPerson(Person person) {
        return personRepository.saveAndFlush(person);
    }

    public List<Person> getPersonsContaining(String word) {
        return personRepository.findAllByFirstNameContainingOrLastNameContaining(word, word);
    }

    public Person addPerson(Person person) {
        return personRepository.saveAndFlush(person);
    }
}

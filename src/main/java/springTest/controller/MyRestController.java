package springTest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springTest.model.Person;
import springTest.service.PersonService;


@RestController
@RequestMapping("/rest")
public class MyRestController {

    @Autowired
    PersonService personService;

    // todo: @ExceptionHandler

    @RequestMapping("/check/{contain}")
    public List<Person> getPersonsContaining(@PathVariable String contain) {
        System.out.println("CONTAINS ?");
        return personService.getPersonsContaining(contain);
    }

    @GetMapping("/remove")
    public Person remove(@RequestParam(value="email") String email) {

        System.out.println("removing: " + email);

        return new Person(email, "bob", "fdiwj");
    }

    @GetMapping("/remove/{email}")
    public Person removeById(@PathVariable String email) {

        System.out.println("removing: " + email);

        return new Person(email, "bob", "fdiwj");
    }

    @RequestMapping("/add")
    public Person addByForm(@ModelAttribute("person") Person person) {
        System.out.println(person);
        return personService.addPerson(person);
    }
}

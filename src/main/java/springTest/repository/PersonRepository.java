package springTest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springTest.model.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    List<Person> findAllByFirstName(String fn);

    List<Person> findAllByFirstNameContainingOrLastNameContaining(String fn, String ln);

}

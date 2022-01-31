package mil.army.futures.asitemplate.team.controller;

import lombok.AllArgsConstructor;
import mil.army.futures.asitemplate.person.entity.PersonRequest;
import mil.army.futures.asitemplate.person.entity.PersonResponse;
import mil.army.futures.asitemplate.person.service.PersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @PostMapping("/person")
    public PersonResponse createPerson(@RequestBody PersonRequest personRequest) {
        return personService.createPerson(personRequest);
    }

}

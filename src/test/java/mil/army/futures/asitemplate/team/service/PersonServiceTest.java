package mil.army.futures.asitemplate.team.service;

import mil.army.futures.asitemplate.person.entity.PersonEntity;
import mil.army.futures.asitemplate.person.entity.PersonRequest;
import mil.army.futures.asitemplate.person.entity.PersonResponse;
import mil.army.futures.asitemplate.person.repository.PersonRepository;
import mil.army.futures.asitemplate.person.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase
class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    TeamService teamService;

    @InjectMocks
    PersonService personService;

    @Test
    void shouldCreatePersonAndReturnNewPersonWithId() {
        var mockedPersonRequest = PersonRequest.builder()
                .name("Person 1")
                .build();
        var mockedPersonEntityRequested = PersonEntity.builder()
                .name("Person 1")
                .build();
        var mockedPersonEntityReturned = PersonEntity.builder()
                .id(1L)
                .name("Person 1")
                .build();
        var mockedPersonResponse = PersonResponse.builder()
                .id(1L)
                .name("Person 1")
                .build();

        when(personRepository.save(mockedPersonEntityRequested)).thenReturn(mockedPersonEntityReturned);

        var createdPerson = personService.createPerson(mockedPersonRequest);

        assertThat(createdPerson).isEqualTo(mockedPersonResponse);
    }
}
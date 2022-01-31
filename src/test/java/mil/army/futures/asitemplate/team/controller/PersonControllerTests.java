package mil.army.futures.asitemplate.team.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mil.army.futures.asitemplate.person.entity.PersonRequest;
import mil.army.futures.asitemplate.person.entity.PersonResponse;
import mil.army.futures.asitemplate.person.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTests {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @Test
    public void whenCreatingPerson_returnsTheCreatedPerson() throws Exception {
        var mockedPersonRequest = PersonRequest.builder().name("Person 1").build();
        var mockedPersonResponse = PersonResponse.builder().id(1L).name("Person 1").build();
        when(personService.createPerson(mockedPersonRequest)).thenReturn(mockedPersonResponse);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockedPersonRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockedPersonResponse)));
    }
}

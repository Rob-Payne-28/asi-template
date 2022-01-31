package mil.army.futures.asitemplate.team.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import mil.army.futures.asitemplate.team.entity.TeamRequest;
import mil.army.futures.asitemplate.team.entity.TeamResponse;
import mil.army.futures.asitemplate.team.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
@AutoConfigureMockMvc
public class TeamControllerTests {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TeamService teamService;

    @Test
    public void whenGettingTeams_returnsAListOfTeams() {
        var mockTeamsList = new ArrayList<TeamResponse>();
        mockTeamsList.add(TeamResponse.builder().name("Team 1").build());
        mockTeamsList.add(TeamResponse.builder().name("Team 2").build());
        when(teamService.getTeams()).thenReturn(mockTeamsList);

        var returnedTeams = teamService.getTeams();

        assertThat(returnedTeams.toString()).isEqualTo(mockTeamsList.toString());
    }

    @Test
    public void whenCreatingTeam_returnsTheCreatedTeam() throws Exception {
        var mockedTeamRequest = TeamRequest.builder().name("Team 1").build();
        var mockedTeamResponse = TeamResponse.builder().id(1L).name("Team 1").build();
        when(teamService.createTeam(mockedTeamRequest)).thenReturn(mockedTeamResponse);

        mockMvc.perform(post("/team")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockedTeamRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockedTeamResponse)));
    }
}
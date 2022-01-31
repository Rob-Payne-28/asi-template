package mil.army.futures.asitemplate.team.service;

import mil.army.futures.asitemplate.person.entity.PersonRequest;
import mil.army.futures.asitemplate.person.entity.PersonResponse;
import mil.army.futures.asitemplate.team.entity.TeamRequest;
import mil.army.futures.asitemplate.team.entity.TeamEntity;
import mil.army.futures.asitemplate.team.entity.TeamResponse;
import mil.army.futures.asitemplate.team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    TeamService teamService;

    @Test
    void shouldGetTeamsFromRepositoryAndReturnTeamResponses() {
        var mockedTeamEntities = new ArrayList<TeamEntity>();
        mockedTeamEntities.add(TeamEntity.builder().id(1L).name("Team 1").build());
        mockedTeamEntities.add(TeamEntity.builder().id(2L).name("Team 2").build());
        when(teamRepository.findAll()).thenReturn(mockedTeamEntities);

        var returnedTeams = teamService.getTeams();

        var mockedTeamResponses = new ArrayList<TeamResponse>();
        mockedTeamResponses.add(TeamResponse.builder().id(1L).name("Team 1").build());
        mockedTeamResponses.add(TeamResponse.builder().id(2L).name("Team 2").build());

        assertThat(returnedTeams).isEqualTo(mockedTeamResponses);
    }

    @Test
    void shouldCreateTeamAndReturnNewTeamWithId() {
        var mockedTeamRequest = TeamRequest.builder()
                .name("Team 1")
                .build();
        var mockedTeamEntityRequested = TeamEntity.builder()
                .name("Team 1")
                .build();
        var mockedTeamEntityReturned = TeamEntity.builder()
                .id(1L)
                .name("Team 1")
                .build();
        var mockedTeamResponse = TeamResponse.builder()
                .id(1L)
                .name("Team 1")
                .build();

        when(teamRepository.save(mockedTeamEntityRequested)).thenReturn(mockedTeamEntityReturned);

        var createdTeam = teamService.createTeam(mockedTeamRequest);

        assertThat(createdTeam).isEqualTo(mockedTeamResponse);
    }
}
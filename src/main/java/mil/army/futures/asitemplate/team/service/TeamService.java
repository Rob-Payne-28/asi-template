package mil.army.futures.asitemplate.team.service;

import lombok.AllArgsConstructor;
import mil.army.futures.asitemplate.team.entity.TeamEntity;
import mil.army.futures.asitemplate.team.entity.TeamRequest;
import mil.army.futures.asitemplate.team.entity.TeamResponse;
import mil.army.futures.asitemplate.team.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class TeamService {
    private TeamRepository teamRepository;

    public ArrayList<TeamResponse> getTeams() {
        var allTeams = teamRepository.findAll();
        var teamResponses = new ArrayList<TeamResponse>();
        for (TeamEntity teamEntity : allTeams) {
            teamResponses.add(
                    TeamResponse.builder()
                            .id(teamEntity.getId())
                            .members(teamEntity.getMembers())
                            .name(teamEntity.getName())
                            .build()
            );
        }
        return teamResponses;
    }

    public TeamResponse createTeam(TeamRequest teamRequest) {

        var newTeamEntity = teamRepository.save(
                TeamEntity.builder()
                        .name(teamRequest.getName())
                        .build()
        );

        return TeamResponse.builder()
                .id(newTeamEntity.getId())
                .name(newTeamEntity.getName())
                .build();
    }

    public TeamEntity findTeamByTeamName(String teamName) {
        return teamRepository.findByName(teamName);
    }

    public void updateTeamMembers(TeamEntity teamEntity) {
        teamRepository.save(teamEntity);
    }
}

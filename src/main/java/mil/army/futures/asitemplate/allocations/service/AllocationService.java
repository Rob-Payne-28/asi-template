package mil.army.futures.asitemplate.allocations.service;

import lombok.AllArgsConstructor;
import mil.army.futures.asitemplate.person.entity.PersonEntity;
import mil.army.futures.asitemplate.team.service.TeamService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AllocationService {
    private TeamService teamService;

    public PersonEntity assignPersonToTeam(PersonEntity personEntity, String teamName) {
        var gainingTeam = teamService.findTeamByTeamName(teamName);
        personEntity.setTeamEntity(gainingTeam);
        return personEntity;
    }
}

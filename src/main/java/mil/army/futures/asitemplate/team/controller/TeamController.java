package mil.army.futures.asitemplate.team.controller;

import lombok.AllArgsConstructor;
import mil.army.futures.asitemplate.person.entity.PersonRequest;
import mil.army.futures.asitemplate.person.entity.PersonResponse;
import mil.army.futures.asitemplate.team.entity.TeamRequest;
import mil.army.futures.asitemplate.team.entity.TeamResponse;
import mil.army.futures.asitemplate.team.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TeamController {
    private TeamService teamService;

    @GetMapping("/teams")
    public List<TeamResponse> getTeam() {
        return teamService.getTeams();
    }

    @PostMapping("/team")
    public TeamResponse createTeam(@RequestBody TeamRequest teamRequest) {
        return teamService.createTeam(teamRequest);
    }
}
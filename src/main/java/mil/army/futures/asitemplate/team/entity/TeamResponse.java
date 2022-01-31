package mil.army.futures.asitemplate.team.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mil.army.futures.asitemplate.person.entity.PersonEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeamResponse {
    private Long id;
    private String name;
    private List<PersonEntity> members;
}

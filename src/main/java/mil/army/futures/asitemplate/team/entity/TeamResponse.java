package mil.army.futures.asitemplate.team.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeamResponse {
    private Long id;
    private String name;
}

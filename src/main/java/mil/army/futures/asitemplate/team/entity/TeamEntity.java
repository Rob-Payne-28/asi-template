package mil.army.futures.asitemplate.team.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import mil.army.futures.asitemplate.person.entity.PersonEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonManagedReference
    @OneToMany(
            mappedBy = "teamEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PersonEntity> members;
}

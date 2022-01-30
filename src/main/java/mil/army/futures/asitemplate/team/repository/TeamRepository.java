package mil.army.futures.asitemplate.team.repository;

import mil.army.futures.asitemplate.team.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}

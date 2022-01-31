package mil.army.futures.asitemplate.team.repository;

import mil.army.futures.asitemplate.team.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    TeamEntity findByName(String teamName);
}

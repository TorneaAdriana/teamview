package eu.accesa.teamview.repository;

import eu.accesa.teamview.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that handles {@link Team} entities.
 */
@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}

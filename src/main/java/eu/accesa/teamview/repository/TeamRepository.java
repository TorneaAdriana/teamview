package eu.accesa.teamview.repository;

import eu.accesa.teamview.model.Team;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository that handles {@link Team} entities.
 */
@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    /**
     * Finds all teams currently in the database.
     * <br/>
     * Overridden to return a {@link List} instead of {@link Iterable}.
     *
     * @return list of teams in the database
     */
    @NonNull
    @Override
    List<Team> findAll();
}

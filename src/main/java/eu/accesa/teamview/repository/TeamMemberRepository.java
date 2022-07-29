package eu.accesa.teamview.repository;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.model.TeamMember;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository that handles {@link TeamMember} entities.
 */
@Repository
public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {

    /**
     * Finds all teams member currently in the database.
     * <br/>
     * Overridden to return a {@link List} instead of {@link Iterable}.
     *
     * @return list of teams member in the database
     */
    @NonNull
    @Override
    List<TeamMember> findAll();
}

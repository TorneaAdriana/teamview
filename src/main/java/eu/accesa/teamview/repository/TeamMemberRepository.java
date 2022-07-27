package eu.accesa.teamview.repository;

import eu.accesa.teamview.model.TeamMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that handles {@link TeamMember} entities.
 */
@Repository
public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {
}

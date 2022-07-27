package eu.accesa.teamview.service;

import eu.accesa.teamview.model.TeamMember;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * Contains business logic related to team member.
 */
@Component
public interface TeamMemberService {

    /**
     * Adds the given team member
     *
     * @param teamMember the team member to be added
     */
    void addTeamMember(@NonNull TeamMember teamMember);

    /**
     * Updates the given team member
     *
     * @param teamMember the team member to be updated
     */
    void updateTeamMember(@NonNull TeamMember teamMember);

    /**
     * Removes the team member with the given id
     *
     * @param id id of the team member to be removed
     */
    void deleteTeamMember(@NonNull Long id);

}

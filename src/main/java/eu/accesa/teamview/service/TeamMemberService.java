package eu.accesa.teamview.service;

import eu.accesa.teamview.model.Team;
import eu.accesa.teamview.model.TeamMember;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Contains business logic related to team member.
 */
public interface TeamMemberService {

    /**
     * Adds the given team member
     *
     * @param teamMember the team member to be added
     */
    void addTeamMember(@NonNull TeamMember teamMember);

    /**
     * @param team the team list to be added in teamMember
     */

    void addTeamToTeamMember(@NonNull List<Team> team, Long id);

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

    /**
     * @return an Optional containing the matching team member if it exists; empty optional otherwise
     */
    Optional<TeamMember> getById(Long id);

    /**
     * @return list of all teams member
     */
    @NonNull
    List<TeamMember> getAllTeamsMember();


}

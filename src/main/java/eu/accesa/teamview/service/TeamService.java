package eu.accesa.teamview.service;

import eu.accesa.teamview.model.Team;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Contains business logic related to team.
 */

public interface TeamService {

    /**
     * Adds the given team
     *
     * @param team the team to be added
     */
    void addTeam(@NonNull Team team);

    /**
     * Updates the given team
     *
     * @param team the team to be updated
     */
    void updateTeam(@NonNull Team team);

    /**
     * Removes the team with the given id
     *
     * @param id id of the team to be removed
     */
    void deleteTeam(@NonNull Long id);

    /**
     * @return an Optional containing the matching team if it exists; empty optional otherwise
     */
    Optional<Team> getById(Long id);

    /**
     * @return list of all teams
     */
    @NonNull
    List<Team> getAllTeams();
}
